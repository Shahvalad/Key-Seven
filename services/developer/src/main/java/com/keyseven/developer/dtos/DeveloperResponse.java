package com.keyseven.developer.dtos;

public record DeveloperResponse(
        Long id,
        String name,
        String description,
        String imageUrl,
        String website,
        String country
) {
}
