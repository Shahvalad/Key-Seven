package com.keyseven.order.clients;

import com.keyseven.order.dtos.GameKeyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "inventory-service", url = "http://localhost:9000")
public interface InventoryClient {
    @GetMapping("/api/v1/inventory/game-keys/game/{gameId}")
    List<GameKeyResponse> getByGameId(@PathVariable Long gameId);
    @PostMapping("/api/v1/inventory/game-keys/redeem/{id}")
    void redeem(@PathVariable Long id);
}
