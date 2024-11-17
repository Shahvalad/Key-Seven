package com.keyseven.game.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "developer-service", url = "http://localhost:8070")
public interface DeveloperClient {
    @GetMapping("/api/v1/developers/exist/{id}")
    boolean isDeveloperExist(@PathVariable Long id);
}
