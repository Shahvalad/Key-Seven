package com.keyseven.genre.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Genre {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ElementCollection
    @CollectionTable(name = "genre_game", joinColumns = @JoinColumn(name = "genre_id"))
    @Column(name = "game_id")
    private List<Long> gameIds;
}
