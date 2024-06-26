package com.skhuthon.caffeinebalance.mypage.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;
    private int caffeine;
    private String brand;
    private String menu;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserMenu> userMenus;
}
