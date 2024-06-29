package com.skhuthon.caffeinebalance.product.domain;

import com.skhuthon.caffeinebalance.user.domain.User;
import jakarta.persistence.*;
import java.util.List;
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

    @ManyToMany(mappedBy = "products")
    private List<User> users;

    @Column(name = "brand")
    private String brand;

    @Column(name = "menu")
    private String menu;

    @Column(name = "caffeine")
    private double caffeine;
}
