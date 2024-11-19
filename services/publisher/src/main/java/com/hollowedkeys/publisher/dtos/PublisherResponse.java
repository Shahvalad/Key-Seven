package com.hollowedkeys.publisher.dtos;

public record PublisherResponse(
        Long id,
        String name,
        String description,
        String imageUrl,
        String website
) {
}
