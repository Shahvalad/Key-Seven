package com.hollowedkeys.publisher.repositories;

import com.hollowedkeys.publisher.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    List<Publisher> findByIsDeletedFalse();
    Optional<Publisher> findByIdAndIsDeletedFalse(Long id);
}
