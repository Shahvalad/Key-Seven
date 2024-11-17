package com.keyseven.game.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "genre-service", url = "${genre-service.url}")
public interface GenreClient {
    @PostMapping("/api/v1/genres/exists")
    boolean checkGenresExist(@RequestBody List<Long> genreIds);
}
