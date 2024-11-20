package com.keyseven.developer.controllers;

import com.keyseven.developer.dtos.DeveloperRequest;
import com.keyseven.developer.dtos.DeveloperResponse;
import com.keyseven.developer.services.DeveloperService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/developers")
public class DeveloperController {
    private final DeveloperService developerService;

    @GetMapping
    public ResponseEntity<List<DeveloperResponse>> getAll() {
        return ResponseEntity.ok(developerService.getAllDevelopers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeveloperResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(developerService.getDeveloperById(id));
    }

    @GetMapping("/exist/{id}")
    public ResponseEntity<Boolean> isExist(@PathVariable Long id) {
        return ResponseEntity.ok(developerService.isDeveloperExist(id));
    }

    @PostMapping
    public ResponseEntity<Long> createDeveloper(@RequestBody @Valid DeveloperRequest developerRequest) {
        return ResponseEntity.ok(developerService.createDeveloper(developerRequest)); // TODO: Change this to return a URI
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDeveloper(@PathVariable Long id, @RequestBody DeveloperRequest developerRequest) {
        developerService.updateDeveloper(id, developerRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeveloper(@PathVariable Long id) {
        developerService.deleteDeveloper(id);
        return ResponseEntity.ok().build();
    }


}
