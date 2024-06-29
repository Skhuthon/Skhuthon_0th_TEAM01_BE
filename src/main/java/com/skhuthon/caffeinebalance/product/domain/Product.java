package com.skhuthon.caffeinebalance.Product.domain;

import com.skhuthon.caffeinebalance.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "menu")
    private String menu;

    @Column(name = "caffeine")
    private double caffeine;
}