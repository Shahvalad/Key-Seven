package com.keyseven.game.dtos;

import java.util.List;

public record GenreResponse(
        Long id,
        String name,
        List<Long> gameIds
) {
}
