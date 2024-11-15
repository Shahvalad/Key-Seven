package com.keyseven.genre.services.Impl;

import com.keyseven.genre.dtos.GenreRequest;
import com.keyseven.genre.dtos.GenreResponse;
import com.keyseven.genre.exceptions.GenreAlreadyExistsException;
import com.keyseven.genre.exceptions.GenreNotFoundException;
import com.keyseven.genre.mappers.GenreMapper;
import com.keyseven.genre.repositories.GenreRepository;
import com.keyseven.genre.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GenreServiceImpl implements GenreService {

    private final KafkaTemplate<String, GenreResponse> kafkaTemplate;
    private static final String TOPIC = "genre-events";

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public List<GenreResponse> getAllGenres() {
        return genreRepository.findAll()
                .stream()
                .map(genreMapper::toResponse).toList();
    }

    @Override
    public GenreResponse getGenreById(Long id) {
        return genreRepository.findById(id)
                .map(genreMapper::toResponse)
                .orElseThrow(()-> new GenreNotFoundException("Genre not found"));
    }

    @Override
    public Long createGenre(GenreRequest request) {
        genreRepository.findByName(request.name())
                .ifPresent(genre -> {
                    throw new GenreAlreadyExistsException("Genre with name '" + request.name() + "' already exists.");
                });
        var genre = genreRepository.save(genreMapper.toEntity(request));
        kafkaTemplate.send(TOPIC, genreMapper.toResponse(genre));
        return genre.getId();
    }

    @Override
    public void updateGenre(Long id, GenreRequest request) {
        var existingGenre = genreRepository.findById(id)
                .orElseThrow(()-> new GenreNotFoundException("Genre not found"));
        existingGenre.setName(request.name());
        if(request.gameIds() != null){
            existingGenre.setGameIds(request.gameIds());
        }
        genreRepository.save(existingGenre);
        kafkaTemplate.send(TOPIC, genreMapper.toResponse(existingGenre));
    }

    @Override
    public void deleteGenre(Long id) {
        var existingGenre = genreRepository.findById(id)
                .orElseThrow(()-> new GenreNotFoundException("Genre not found"));
        genreRepository.delete(existingGenre);
        kafkaTemplate.send(TOPIC, genreMapper.toResponse(existingGenre));
    }
}
