package com.keyseven.game.services.Impl;

import com.keyseven.game.clients.GenreClient;
import com.keyseven.game.dtos.GameRequest;
import com.keyseven.game.dtos.GameResponse;
import com.keyseven.game.exceptions.GameNotFoundException;
import com.keyseven.game.exceptions.GenreIdsEmptyException;
import com.keyseven.game.exceptions.GenreNotFoundException;
import com.keyseven.game.mappers.GameMapper;
import com.keyseven.game.repositories.GameRepository;
import com.keyseven.game.services.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GameServiceImpl implements GameService {


    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    private final GenreClient genreClient;


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
        boolean genresExist = validateGenres(request.genreIds());
        if (!genresExist) {
            throw new GenreNotFoundException("One or more genres do not exist or are deleted.");
        }
        var game = gameRepository.save(gameMapper.toGame(request));
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

    private boolean validateGenres(List<Long> genreIds) {
        if (genreIds == null || genreIds.isEmpty()) {
            throw new GenreIdsEmptyException("Genre IDs cannot be null or empty.");
        }
        return genreClient.checkGenresExist(genreIds);
    }
}
