package com.hollowedkeys.inventory.mappers;

import com.hollowedkeys.inventory.dtos.GameKeyRequest;
import com.hollowedkeys.inventory.dtos.GameKeyResponse;
import com.hollowedkeys.inventory.entities.GameKey;
import org.springframework.stereotype.Component;

@Component
public class GameKeyMapper {

    public GameKeyResponse toGameKeyResponse(GameKey gameKey){
        return new GameKeyResponse(
                gameKey.getId(),
                gameKey.getKeyCode(),
                gameKey.getGameId(),
                gameKey.isUsed(),
                gameKey.getAddedDate()
        );
    }

    public GameKey toGameKey(GameKeyRequest gameKeyRequest){
        return GameKey.builder()
                .keyCode(gameKeyRequest.keyCode())
                .gameId(gameKeyRequest.gameId())
                .build();
    }
}
