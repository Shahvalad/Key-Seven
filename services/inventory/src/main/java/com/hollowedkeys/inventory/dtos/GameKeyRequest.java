package com.hollowedkeys.inventory.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record GameKeyRequest(
    @NotBlank(message = "Key code must not be blank")
    String keyCode,
    @NotNull(message = "Game ID cannot be null")
    @Positive(message = "Game ID must be a positive number")
    Long gameId
) {
}
