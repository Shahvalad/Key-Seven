package com.hollowedkeys.publisher.exceptions;

public class PublisherNotFoundException extends RuntimeException {
    public PublisherNotFoundException(Long id) {
        super("Publisher with id " + id + " not found");
    }
}
