package com.hollowedkeys.inventory.clients;

import jakarta.ws.rs.Path;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "game-service", url = "http://localhost:8050")
public interface GameClient {
    @GetMapping("/api/v1/games/exists/{id}")
    boolean gameExists(@PathVariable Long id);
}
