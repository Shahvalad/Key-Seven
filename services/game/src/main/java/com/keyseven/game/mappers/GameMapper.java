package com.keyseven.game.mappers;

import com.keyseven.game.dtos.GameRequest;
import com.keyseven.game.dtos.GameResponse;
import com.keyseven.game.entities.Game;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {

    public GameResponse toGameResponse(Game game) {
        return new GameResponse(
                game.getId(),
                game.getName(),
                game.getDescription(),
                game.getPlatform(),
                game.getPrice(),
                game.getReleaseDate(),
                game.getImageUrl(),
                game.getGenreIds(),
                game.getDeveloperId(),
                game.getPublisherId()
        );
    }

    public Game toGame(GameRequest gameRequest) {
        return Game.builder()
                .name(gameRequest.name())
                .description(gameRequest.description())
                .platform(gameRequest.platform())
                .price(gameRequest.price())
                .releaseDate(gameRequest.releaseDate())
                .imageUrl(gameRequest.imageUrl())
                .genreIds(gameRequest.genreIds())
                .developerId(gameRequest.developerId())
                .publisherId(gameRequest.publisherId())
                .build();
    }

    public void updateGameFromRequest(GameRequest gameRequest, Game existingGame) {
        if (gameRequest.name() != null) {
            existingGame.setName(gameRequest.name());
        }
        if (gameRequest.description() != null) {
            existingGame.setDescription(gameRequest.description());
        }
        if (gameRequest.platform() != null) {
            existingGame.setPlatform(gameRequest.platform());
        }
        if (gameRequest.price() != null) {
            existingGame.setPrice(gameRequest.price());
        }
        if (gameRequest.releaseDate() != null) {
            existingGame.setReleaseDate(gameRequest.releaseDate());
        }
        if (gameRequest.imageUrl() != null) {
            existingGame.setImageUrl(gameRequest.imageUrl());
        }
        if (gameRequest.genreIds() != null) {
            existingGame.setGenreIds(gameRequest.genreIds());
        }
        if (gameRequest.developerId() != null) {
            existingGame.setDeveloperId(gameRequest.developerId());
        }
        if (gameRequest.publisherId() != null) {
            existingGame.setPublisherId(gameRequest.publisherId());
        }
    }



}
