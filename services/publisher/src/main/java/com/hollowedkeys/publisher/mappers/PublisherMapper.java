package com.hollowedkeys.publisher.mappers;

import com.hollowedkeys.publisher.dtos.PublisherRequest;
import com.hollowedkeys.publisher.dtos.PublisherResponse;
import com.hollowedkeys.publisher.entities.Publisher;
import org.springframework.stereotype.Component;

@Component
public class PublisherMapper {

    public PublisherResponse toResponse(Publisher publisher) {
        return new PublisherResponse(
                publisher.getId(),
                publisher.getName(),
                publisher.getDescription(),
                publisher.getImageUrl(),
                publisher.getWebsite()
        );
    }

    public Publisher toEntity(PublisherRequest request){
        return Publisher.builder()
                .name(request.name())
                .description(request.description())
                .website(request.website())
                .imageUrl(request.imageUrl())
                .build();
    }
}
