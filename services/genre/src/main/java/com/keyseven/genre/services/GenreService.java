package com.keyseven.genre.services;

import com.keyseven.genre.dtos.GenreRequest;
import com.keyseven.genre.dtos.GenreResponse;

import java.util.List;

public interface GenreService {
    List<GenreResponse> getAllGenres();
    GenreResponse getGenreById(Long id);
    Long createGenre(GenreRequest request);
    void updateGenre(Long id, GenreRequest request);
    void deleteGenre(Long id);
    boolean doGenresExist(List<Long> ids);
}
