package com.keyseven.game.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "publisher-service",  url = "${publisher-service.url}")
public interface PublisherClient {
    @GetMapping("/api/v1/publishers/exist/{id}")
    boolean isPublisherExist(@PathVariable Long id);
}
