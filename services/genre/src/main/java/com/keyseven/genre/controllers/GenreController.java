package com.keyseven.genre.controllers;

import com.keyseven.genre.dtos.GenreRequest;
import com.keyseven.genre.dtos.GenreResponse;
import com.keyseven.genre.services.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/genres")
public class GenreController {
    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreResponse>> getAll(){
        return ResponseEntity.ok(genreService.getAllGenres());
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<GenreResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(genreService.getGenreById(id));
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody @Valid GenreRequest request){
        return ResponseEntity.ok(genreService.createGenre(request));
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid GenreRequest request){
        genreService.updateGenre(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable Long id){
        genreService.deleteGenre(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/exists")
    public ResponseEntity<Boolean> checkGenresExist(@RequestBody List<Long> ids) {
        boolean allExist = genreService.doGenresExist(ids);
        return ResponseEntity.ok(allExist);
    }
}
