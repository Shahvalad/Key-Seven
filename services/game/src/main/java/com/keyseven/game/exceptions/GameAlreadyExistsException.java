package com.keyseven.game.exceptions;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class GameAlreadyExistsException extends RuntimeException {
    public GameAlreadyExistsException(String s) {
        super(s);
    }
}
