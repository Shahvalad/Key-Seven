package com.keyseven.genre.exceptions;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class GenreAlreadyExistsException extends RuntimeException {
    public GenreAlreadyExistsException(String message) {
        super(message);
    }
}
