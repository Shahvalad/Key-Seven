package com.keyseven.developer.dtos;

public record DeveloperRequest(
        String name,
        String description,
        String imageUrl,
        String website,
        String country
) {
}
