package com.keyseven.developer.repositories;

import com.keyseven.developer.entites.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeveloperRepository extends JpaRepository<Developer,Long> {
    List<Developer> findByIsDeletedFalse();
    Optional<Developer> findByIdAndIsDeletedFalse(Long id);
}
