package com.hollowedkeys.publisher.services.Impl;

import com.hollowedkeys.publisher.dtos.PublisherRequest;
import com.hollowedkeys.publisher.dtos.PublisherResponse;
import com.hollowedkeys.publisher.entities.Publisher;
import com.hollowedkeys.publisher.exceptions.PublisherNotFoundException;
import com.hollowedkeys.publisher.mappers.PublisherMapper;
import com.hollowedkeys.publisher.repositories.PublisherRepository;
import com.hollowedkeys.publisher.services.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper mapper;

    @Override
    public List<PublisherResponse> getAll() {
        return publisherRepository.findByIsDeletedFalse()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public PublisherResponse getById(Long id) {
        return publisherRepository.findByIdAndIsDeletedFalse(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new PublisherNotFoundException(id));
    }

    @Override
    public Long create(PublisherRequest request) {
        return publisherRepository.save(mapper.toEntity(request)).getId();
    }

    @Override
    public void update(Long id, PublisherRequest request) {
        var existingPublisher = publisherRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new PublisherNotFoundException(id));
        updatePublisherFields(existingPublisher, request);
        publisherRepository.save(existingPublisher);
    }

    @Override
    public void delete(Long id) {
        var existingPublisher = publisherRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new PublisherNotFoundException(id));
        existingPublisher.setDeleted(true);
        publisherRepository.save(existingPublisher);
    }

    @Override
    public boolean isExist(Long id) {
        return publisherRepository.findByIdAndIsDeletedFalse(id).isPresent();
    }

    private void updatePublisherFields(Publisher publisher, PublisherRequest request) {
        if(request.name() != null){
            publisher.setName(request.name());
        }
        if(request.description() != null){
            publisher.setDescription(request.description());
        }
        if(request.website() != null){
            publisher.setWebsite(request.website());
        }
        if(request.imageUrl() != null) {
            publisher.setImageUrl(request.imageUrl());
        }
    }
}
