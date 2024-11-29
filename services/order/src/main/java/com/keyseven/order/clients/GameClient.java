package com.keyseven.order.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "game-service", url = "http://localhost:8050")
public interface GameClient {
    @GetMapping("/api/v1/games/exists/{id}")
    boolean exists(@PathVariable Long id);
}
