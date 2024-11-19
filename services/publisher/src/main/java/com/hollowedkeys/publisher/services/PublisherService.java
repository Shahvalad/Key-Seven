package com.hollowedkeys.publisher.services;

import com.hollowedkeys.publisher.dtos.PublisherRequest;
import com.hollowedkeys.publisher.dtos.PublisherResponse;

import java.util.List;

public interface PublisherService {
    List<PublisherResponse> getAll();
    PublisherResponse getById(Long id);
    Long create(PublisherRequest request);
    void update(Long id, PublisherRequest request);
    void delete(Long id);
    boolean isExist(Long id);
}
