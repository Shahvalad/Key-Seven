package com.keyseven.order.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "_orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    @ElementCollection
    private List<Long> gameIds;
    private BigDecimal totalPrice;
    private LocalDateTime orderDate;
}
