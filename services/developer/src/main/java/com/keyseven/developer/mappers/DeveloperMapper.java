package com.keyseven.developer.mappers;

import com.keyseven.developer.dtos.DeveloperRequest;
import com.keyseven.developer.dtos.DeveloperResponse;
import com.keyseven.developer.entites.Developer;
import org.springframework.stereotype.Component;

@Component
public class DeveloperMapper {

    public Developer toEntity(DeveloperRequest request) {
        return Developer.builder()
                .name(request.name())
                .description(request.description())
                .imageUrl(request.imageUrl())
                .website(request.website())
                .country(request.country())
                .build();
    }

    public DeveloperResponse toResponse(Developer entity) {
        return new DeveloperResponse(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getImageUrl(),
                entity.getWebsite(),
                entity.getCountry()
        );
    }
}
