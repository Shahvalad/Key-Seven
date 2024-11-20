package com.hollowedkeys.inventory.exceptions;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException() {
        super("Game not found");
    }
}
