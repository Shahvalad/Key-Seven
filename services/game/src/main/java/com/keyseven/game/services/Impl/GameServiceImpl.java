package com.keyseven.game.services.Impl;

import com.keyseven.game.dtos.GameRequest;
import com.keyseven.game.dtos.GameResponse;
import com.keyseven.game.dtos.GenreResponse;
import com.keyseven.game.exceptions.GameNotFoundException;
import com.keyseven.game.mappers.GameMapper;
import com.keyseven.game.repositories.GameRepository;
import com.keyseven.game.services.GameService;
import com.keyseven.game.services.Impl.GenreCacheService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GameServiceImpl implements GameService {

    private static final Logger logger = LoggerFactory.getLogger(GameServiceImpl.class); // Logger instance

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    private final KafkaTemplate<String, GameResponse> kafkaTemplate;
    private final GenreCacheService genreCacheService; // Inject GenreCacheService

    private static final String GAME_TOPIC = "game-events";

    // Listen for genre events to update the cache of genre IDs
    @KafkaListener(topics = "genre-events", groupId = "game-service")
    public void listen(GenreResponse genreResponse) {
        logger.debug("Received GenreResponse: {}", genreResponse); // Log the received GenreResponse
        genreCacheService.addGenreToCache(genreResponse); // Assuming you have an addGenreToCache method
    }

    @Override
    public List<GameResponse> getAllGames() {
        return gameRepository.findAll()
                .stream()
                .map(gameMapper::toGameResponse)
                .toList();
    }

    @Override
    public GameResponse getGameById(Long id) {
        return gameRepository.findById(id)
                .map(gameMapper::toGameResponse)
                .orElseThrow(() -> new GameNotFoundException("Game not found with id: " + id));
    }

    @Override
    public Long createGame(GameRequest request) {
        if (request.genreIds().stream().anyMatch(id -> !genreCacheService.genreExists(id))) {
            throw new IllegalArgumentException("One or more genres do not exist.");
        }

        var game = gameRepository.save(gameMapper.toGame(request));
        kafkaTemplate.send(GAME_TOPIC, gameMapper.toGameResponse(game));
        return game.getId();
    }

    @Override
    public void updateGameById(Long id, GameRequest gameRequest) {
        var existingGame = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException("Game not found with id: " + id));
        gameMapper.updateGameFromRequest(gameRequest, existingGame);
        gameRepository.save(existingGame);
    }

    @Override
    public void deleteGameById(Long id) {
        var existingGame = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException("Game not found with id: " + id));
        gameRepository.delete(existingGame);
    }
}
