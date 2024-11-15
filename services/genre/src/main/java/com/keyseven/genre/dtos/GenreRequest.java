package com.keyseven.genre.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record GenreRequest(
        @NotEmpty(message = "Name is required")
        @NotBlank(message = "Name is required")
        @NotNull(message = "Name is required")
        String name,
        List<Long> gameIds
) {
}
