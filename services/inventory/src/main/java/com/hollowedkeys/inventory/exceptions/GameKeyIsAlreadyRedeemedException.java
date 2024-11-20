package com.hollowedkeys.inventory.exceptions;

public class GameKeyIsAlreadyRedeemedException extends RuntimeException {
    public GameKeyIsAlreadyRedeemedException(String s) {
        super(s);
    }
}
