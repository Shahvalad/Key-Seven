package com.hollowedkeys.inventory.controllers;

import com.hollowedkeys.inventory.dtos.GameKeyRequest;
import com.hollowedkeys.inventory.dtos.GameKeyResponse;
import com.hollowedkeys.inventory.services.GameKeyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventory/game-keys")
public class GameKeyController {

    private final GameKeyService gameKeyService;

    @GetMapping()
    public ResponseEntity<List<GameKeyResponse>> getAll() {
        return ResponseEntity.ok(gameKeyService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameKeyResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(gameKeyService.getById(id));
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<GameKeyResponse>> getByGameId(@PathVariable Long gameId) {
        return ResponseEntity.ok(gameKeyService.findByGameId(gameId));
    }

    @PostMapping("/redeem/{id}")
    public ResponseEntity<Void> redeem(@PathVariable Long id) {
        gameKeyService.redeem(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<Long> create(@Valid @RequestBody GameKeyRequest gameKeyRequest) {
        Long createdId = gameKeyService.create(gameKeyRequest);
        return ResponseEntity.created(URI.create("/api/v1/inventory/game-keys/" + createdId)).body(createdId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody GameKeyRequest gameKeyRequest) {
        gameKeyService.update(id, gameKeyRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        gameKeyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

