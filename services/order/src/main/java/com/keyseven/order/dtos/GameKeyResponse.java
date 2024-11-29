package com.keyseven.order.dtos;

import java.time.LocalDateTime;

public record GameKeyResponse(
        Long id,
        String keyCode,
        Long gameId,
        boolean isUsed,
        LocalDateTime addedDate
) {
}