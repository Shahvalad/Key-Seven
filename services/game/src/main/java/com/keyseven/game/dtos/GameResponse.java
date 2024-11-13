package com.keyseven.game.dtos;

import com.keyseven.game.entities.Platform;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record GameResponse(
        Long id,
        String name,
        String description,
        Platform platform,
        BigDecimal price,
        LocalDateTime releaseDate,
        String imageUrl,
        List<Long> genres,
        Long developerId,
        Long publisherId
) {
}
