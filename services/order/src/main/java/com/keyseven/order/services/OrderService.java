package com.keyseven.order.services;

import com.keyseven.order.dtos.OrderRequest;
import com.keyseven.order.dtos.OrderResponse;

import java.util.List;

public interface OrderService {
    List<OrderResponse> getOrders();
    OrderResponse getOrder(Long orderId);
    Long createOrder(OrderRequest orderRequest);
    void updateOrder(Long orderId, OrderRequest orderRequest);
    void deleteOrder(Long orderId);
}
