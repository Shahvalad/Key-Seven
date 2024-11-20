package com.hollowedkeys.inventory.services;

import com.hollowedkeys.inventory.dtos.GameKeyRequest;
import com.hollowedkeys.inventory.dtos.GameKeyResponse;

import java.util.List;

public interface GameKeyService {
    List<GameKeyResponse> getAll();
    GameKeyResponse getById(Long id);
    Long create(GameKeyRequest gameKeyRequest);
    void update(Long id, GameKeyRequest gameKeyRequest);
    void delete(Long id);
    void redeem(Long id);
    List<GameKeyResponse> findByGameId(Long gameId);
}
