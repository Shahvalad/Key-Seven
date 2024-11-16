package com.keyseven.genre.entities;

import jakarta.persistence.*;
import lombok.*;


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
    private boolean isDeleted = false;
}
