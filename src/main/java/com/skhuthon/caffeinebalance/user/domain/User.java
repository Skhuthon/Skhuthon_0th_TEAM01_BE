package com.skhuthon.caffeinebalance.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_info")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
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

    @Column(name = "recommended_caffeine_intake_amount")
    private int recommendedCaffeineIntakeAmount;

    @Column(name = "today_caffeine_intake_amount")
    private int todayCaffeineIntakeAmount;

    @Column(name = "can_caffeiene_intake_amount")
    private int canCaffeineIntakeAmount;

    @Column(name = "height")
    private Double height;

    @Column(name = "weight")
    private Double weight;

    public void update(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void updateHeightAndWeight(Double height, Double weight) {
        this.height = height;
        this.weight = weight;
    }
}
