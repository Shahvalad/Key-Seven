package com.keyseven.order.mappers;

import com.keyseven.order.dtos.OrderRequest;
import com.keyseven.order.dtos.OrderResponse;
import com.keyseven.order.entities.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getGameIds(),
                order.getTotalPrice()
        );
    }

    public Order toOrder(OrderRequest orderRequest) {
        return Order.builder()
                .gameIds(orderRequest.gameIds())
                .totalPrice(orderRequest.totalPrice())
                .build();
    }
}
