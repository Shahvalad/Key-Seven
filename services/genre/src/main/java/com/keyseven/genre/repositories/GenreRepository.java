package com.keyseven.genre.repositories;

import com.keyseven.genre.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {
    Optional<Genre> findByName(String name);
    long countByIdIn(List<Long> ids);

}
