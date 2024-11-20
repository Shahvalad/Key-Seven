package com.hollowedkeys.inventory.services.Impl;

import com.hollowedkeys.inventory.clients.GameClient;
import com.hollowedkeys.inventory.dtos.GameKeyRequest;
import com.hollowedkeys.inventory.dtos.GameKeyResponse;
import com.hollowedkeys.inventory.exceptions.GameKeyNotFoundException;
import com.hollowedkeys.inventory.exceptions.GameNotFoundException;
import com.hollowedkeys.inventory.mappers.GameKeyMapper;
import com.hollowedkeys.inventory.repositories.GameKeyRepository;
import com.hollowedkeys.inventory.services.GameKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GameKeyServiceImpl implements GameKeyService {

    private final GameKeyRepository gameKeyRepository;
    private final GameKeyMapper gameKeyMapper;
    private final GameClient gameClient;


    @Transactional(readOnly = true)
    @Override
    public List<GameKeyResponse> getAll() {
        return gameKeyRepository.findByIsUsedFalse().stream()
                .map(gameKeyMapper::toGameKeyResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public GameKeyResponse getById(Long id) {
        var gameKey = gameKeyRepository.findByIsUsedFalseAndId(id)
                .orElseThrow(()-> new GameKeyNotFoundException("Game key not found"));
        return gameKeyMapper.toGameKeyResponse(gameKey);
    }

    @Override
    public Long create(GameKeyRequest gameKeyRequest) {
        if(!gameClient.gameExists(gameKeyRequest.gameId())){
            throw new GameNotFoundException();
        }
        return gameKeyRepository.save(gameKeyMapper.toGameKey(gameKeyRequest)).getId();
    }

    @Override
    public void update(Long id, GameKeyRequest gameKeyRequest) {
        var gameKey = gameKeyRepository.findById(id)
                .orElseThrow(()-> new GameKeyNotFoundException("Game key not found"));
        gameKey.setKeyCode(gameKeyRequest.keyCode());
        gameKey.setGameId(gameKeyRequest.gameId());
        gameKeyRepository.save(gameKey);
    }

    @Override
    public void delete(Long id) {
        var gameKey = gameKeyRepository.findById(id)
                .orElseThrow(() -> new GameKeyNotFoundException("Game key not found"));
        gameKeyRepository.delete(gameKey);
    }

    @Override
    public void redeem(Long id) {
        var gameKey = gameKeyRepository.findByIsUsedFalseAndId(id)
                .orElseThrow(() -> new GameKeyNotFoundException("Game key not found or is already redeemed"));
        gameKey.setUsed(true);
        gameKeyRepository.save(gameKey);
    }

    @Override
    public List<GameKeyResponse> findByGameId(Long gameId) {
        var gameKey = gameKeyRepository.findByIsUsedFalseAndGameId(gameId)
                .orElseThrow(() -> new GameKeyNotFoundException("Game key not found"));
        return List.of(gameKeyMapper.toGameKeyResponse(gameKey));
    }

}
