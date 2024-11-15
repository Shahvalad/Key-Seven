package com.keyseven.genre.dtos;

import java.util.List;

public record GenreResponse(
        Long id,
        String name,
        List<Long> gameIds
) {
}
