package com.keyseven.genre.exceptions;

public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException(String s) {
        super(s);
    }
}
