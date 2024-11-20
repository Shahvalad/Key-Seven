package com.keyseven.game.controllers;

import com.keyseven.game.dtos.GameRequest;
import com.keyseven.game.dtos.GameResponse;
import com.keyseven.game.services.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/games")
public class GameController {
    private final GameService gameService;

    @GetMapping
    public ResponseEntity<List<GameResponse>> getAll(){
        return ResponseEntity.ok(gameService.getAllGames());
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<GameResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(gameService.getGameById(id));
    }

    @PostMapping
    public ResponseEntity<Long> createGame(@RequestBody @Valid GameRequest request){
        return ResponseEntity.ok(gameService.createGame(request)); // TODO: Change this to uri
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Void> updateGame(@PathVariable Long id, @RequestBody @Valid GameRequest request) {
        gameService.updateGameById(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteGame(@PathVariable Long id){
        gameService.deleteGameById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping({"/exists/{id}"})
    public ResponseEntity<Boolean> exists(@PathVariable Long id){
        return ResponseEntity.ok(gameService.gameExists(id));
    }
}
