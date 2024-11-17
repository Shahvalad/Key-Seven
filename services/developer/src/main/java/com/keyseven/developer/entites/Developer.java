package com.keyseven.developer.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Developer {
    @Id
    @GeneratedValue
    public Long id;
    public String name;
    public String description;
    public String imageUrl;
    public String website;
    public String country;
    public boolean isDeleted = false;
}
