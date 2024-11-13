package com.keyseven.game.services.Impl;

import com.keyseven.game.dtos.GameRequest;
import com.keyseven.game.dtos.GameResponse;
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

    @Override
    public Long createGame(GameRequest gameRequest) {
        return null;
    }

    @Override
    public List<GameResponse> getAllGames() {
        return List.of();
    }

    @Override
    public GameResponse getGameById(Long id) {
        return null;
    }

    @Override
    public void updateGameById(Long id, GameRequest gameRequest) {

    }

    @Override
    public void deleteGameById(Long id) {

    }
}
