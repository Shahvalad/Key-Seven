package com.hollowedkeys.inventory.repositories;

import com.hollowedkeys.inventory.entities.GameKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameKeyRepository extends JpaRepository<GameKey, Long> {
    List<GameKey> findByIsUsedFalse();
    Optional<GameKey> findByIsUsedFalseAndGameId(Long gameId);
    Optional<GameKey> findByIsUsedFalseAndId(Long id);
}
