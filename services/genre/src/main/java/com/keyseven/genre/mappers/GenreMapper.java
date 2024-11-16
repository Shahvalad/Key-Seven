package com.keyseven.genre.mappers;

import com.keyseven.genre.dtos.GenreRequest;
import com.keyseven.genre.dtos.GenreResponse;
import com.keyseven.genre.entities.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

    public GenreResponse toResponse(Genre genre) {
        return new GenreResponse(
                genre.getId(),
                genre.getName()
        );
    }

    public Genre toEntity(GenreRequest request) {
        return Genre.builder()
                .name(request.name())
                .build();
    }

}
