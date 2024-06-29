package com.skhuthon.caffeinebalance.user.domain;

import com.skhuthon.caffeinebalance.product.domain.Product;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "User_Products",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "profile", columnDefinition = "TEXT")
    private String profile;

    @Column(name = "today_caffeine_intake_amount")
    private double todayCaffeineIntakeAmount;

    @Column(name = "can_caffeiene_intake_amount")
    private double canCaffeineIntakeAmount;

    @Column(name ="daily_recommended_Caffeine_Amount")
    private double dailyRecommendedCaffeineAmount;

    @PrePersist
    public void prePersist() {
        this.products = this.products == null ? new ArrayList<>() : this.products;
    }

    public void update(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void updateCaffeineInformation(double caffeine, double canCaffeineIntakeAmount) {
        this.dailyRecommendedCaffeineAmount -= caffeine;
        this.todayCaffeineIntakeAmount += caffeine;
        this.canCaffeineIntakeAmount = canCaffeineIntakeAmount;
    }

    public void resetDailyCaffeine() {
        this.canCaffeineIntakeAmount = 400D;
        this.todayCaffeineIntakeAmount= 0D;
        this.dailyRecommendedCaffeineAmount = 400D;
    }

    public void resetProducts() {
        this.products = new ArrayList<>();
    }
}
