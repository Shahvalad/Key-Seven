package com.hollowedkeys.inventory.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GameKey {
    @Id
    @GeneratedValue
    private Long id;

    private String keyCode;

    private Long gameId;

    private boolean isUsed = false;

    @CreatedDate
    private LocalDateTime addedDate;
}
