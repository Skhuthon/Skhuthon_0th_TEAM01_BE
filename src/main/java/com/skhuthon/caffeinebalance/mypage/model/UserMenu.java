package com.skhuthon.caffeinebalance.mypage.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.cache.CacheProperties;

@Entity
public class UserMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "")
    private User user;

    @ManyToOne
    @JoinColumn(name = "")
    private Caffeine caffeine;
}
