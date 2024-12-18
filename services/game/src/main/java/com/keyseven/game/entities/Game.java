package com.keyseven.game.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Game {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String name;
    private String description;
    private Platform platform;
    @NotNull
    private BigDecimal price;
    private String imageUrl;
    private LocalDateTime releaseDate;
    @ElementCollection
    private List<Long> genreIds;
    private Long developerId;
    private Long publisherId;
}
