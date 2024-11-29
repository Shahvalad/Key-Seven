package com.keyseven.order.dtos;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponse(
        Long id,
        List<Long> gameIds,
        BigDecimal totalPrice
) {
}
