package com.hollowedkeys.publisher.controllers;

import com.hollowedkeys.publisher.dtos.PublisherRequest;
import com.hollowedkeys.publisher.dtos.PublisherResponse;
import com.hollowedkeys.publisher.services.PublisherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping
    public ResponseEntity<List<PublisherResponse>> getAll(){
        return ResponseEntity.ok(publisherService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(publisherService.getById(id));
    }

    @GetMapping("/exist/{id}")
    public ResponseEntity<Boolean> isExist(@PathVariable Long id){
        return ResponseEntity.ok(publisherService.isExist(id));
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody @Valid PublisherRequest request){
        return ResponseEntity.ok(publisherService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid PublisherRequest request){
        publisherService.update(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        publisherService.delete(id);
        return ResponseEntity.ok().build();
    }

}
