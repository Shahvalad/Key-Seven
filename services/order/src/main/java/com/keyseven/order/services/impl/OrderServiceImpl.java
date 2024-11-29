package com.keyseven.order.services.impl;

import com.keyseven.order.clients.GameClient;
import com.keyseven.order.clients.InventoryClient;
import com.keyseven.order.clients.MailClient;
import com.keyseven.order.dtos.OrderRequest;
import com.keyseven.order.dtos.OrderResponse;
import com.keyseven.order.mappers.OrderMapper;
import com.keyseven.order.repositories.OrderRepository;
import com.keyseven.order.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final InventoryClient inventoryClient;
    private final GameClient gameClient;
    private final MailClient mailClient;

    @Override
    public List<OrderResponse> getOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toOrderResponse)
                .toList();
    }

    @Override
    public OrderResponse getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::toOrderResponse)
                .orElseThrow();//TODO: throw exception
    }

    @Override
    @Transactional
    public Long createOrder(OrderRequest orderRequest) {
        List<String> gameKeys = new ArrayList<>();
        for (var gameId:orderRequest.gameIds())
        {
            if (!gameClient.exists(gameId))
            {
                throw new RuntimeException("Game with id " + gameId + " does not exist");
            }
            var gameKeyResponses = inventoryClient.getByGameId(gameId);
            if (gameKeyResponses.isEmpty())
            {
                throw new RuntimeException("No game keys available for game with id " + gameId);
            }
            for (var gameKeyResponse:gameKeyResponses)
            {
                gameKeys.add(gameKeyResponse.keyCode());
                inventoryClient.redeem(gameKeyResponse.id());
            }
        }
        mailClient.sendEmail("shahvaladayvazov@gmail.com", "Order created", "Your order has been created. Game keys: " + gameKeys);
        return orderRepository.save(orderMapper.toOrder(orderRequest)).getId();
    }

    @Override
    public void updateOrder(Long orderId, OrderRequest orderRequest) {
        var existingOrder = orderRepository.findById(orderId)
                .orElseThrow();//TODO: throw exception
        var updatedOrder = orderMapper.toOrder(orderRequest);
        updatedOrder.setId(existingOrder.getId());
        orderRepository.save(updatedOrder);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
