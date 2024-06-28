package com.skhuthon.caffeinebalance.mainpage.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "drinks")
public class MainPageDrink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drink_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "caffeine_amount", nullable = false)
    private int caffeineAmount;
}
