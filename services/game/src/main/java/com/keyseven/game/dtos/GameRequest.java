package com.keyseven.game.dtos;

import com.keyseven.game.entities.Platform;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record GameRequest(
        @NotNull(message = "Name is required")
        @NotEmpty(message = "Name is required")
        @NotBlank(message = "Name is required")
        String name,
        String description,
        @NotNull(message = "Platform is required")
        Platform platform,
        @NotNull(message = "Price is required")
        @Positive(message = "Price must be greater than 0")
        BigDecimal price,
        LocalDateTime releaseDate,
        String imageUrl,
        List<Long> genreIds,
        @NotNull(message = "Developer ID is required")
        @Positive(message = "Developer ID must be greater than 0")
        Long developerId,
        @NotNull(message = "Publisher ID is required")
        @Positive(message = "Publisher ID must be greater than 0")
        Long publisherId
) {
}
