package com.keyseven.game.services;

import com.keyseven.game.dtos.GameRequest;
import com.keyseven.game.dtos.GameResponse;

import java.util.List;

public interface GameService {
    Long createGame(GameRequest gameRequest);
    List<GameResponse> getAllGames();
    GameResponse getGameById(Long id);
    void updateGameById(Long id, GameRequest gameRequest);
    void deleteGameById(Long id);
}
