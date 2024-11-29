package com.keyseven.order.dtos;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        List<Long> gameIds,
        BigDecimal totalPrice
) {
}
