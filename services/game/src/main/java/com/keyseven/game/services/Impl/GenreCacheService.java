package com.keyseven.game.services.Impl;

import com.keyseven.game.dtos.GenreResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GenreCacheService {

    private static final Logger logger = LoggerFactory.getLogger(GenreCacheService.class);
    private final Map<Long, GenreResponse> genreCache = new HashMap<>();

    @KafkaListener(topics = "genre-events", groupId = "game-service")
    public void listen(GenreResponse genreResponse) {
        if (genreResponse == null || genreResponse.id() == null) {
            logger.warn("Received null or invalid genre response: {}", genreResponse);
            return;
        }
        logger.debug("Received GenreResponse: {}", genreResponse);

        // Add or update genre in the cache
        genreCache.put(genreResponse.id(), genreResponse);
        logger.info("Added genre with ID {} to the cache", genreResponse.id());
    }

    // Method to check if a genre exists in the cache
    public boolean genreExists(Long genreId) {
        if (genreId == null) {
            logger.warn("Received null genreId for checking existence");
            return false;
        }

        boolean exists = genreCache.containsKey(genreId);
        logger.debug("Genre ID {} exists in the cache: {}", genreId, exists);
        return exists;
    }

    // Method to fetch genre by ID from the cache
    public GenreResponse getGenreFromCache(Long genreId) {
        if (genreId == null) {
            logger.warn("Received null genreId for fetching genre from cache");
            return null;
        }

        GenreResponse genreResponse = genreCache.get(genreId);
        if (genreResponse == null) {
            logger.warn("Genre with ID {} not found in the cache", genreId);
        } else {
            logger.debug("Fetched genre with ID {} from cache", genreId);
        }
        return genreResponse;
    }

    // Method to explicitly add a genre to the cache (this is what you were asking for)
    public void addGenreToCache(GenreResponse genreResponse) {
        if (genreResponse == null || genreResponse.id() == null) {
            logger.warn("Received null or invalid genre for adding to cache: {}", genreResponse);
            return;
        }
        genreCache.put(genreResponse.id(), genreResponse);
        logger.info("Explicitly added genre with ID {} to the cache", genreResponse.id());
    }
}
