package com.skhuthon.caffeinebalance.mainpage.dto.request;

import lombok.Getter;

@Getter
public class MainPageRequestDTO {
    private int recommendedCaffeineIntakeAmount;
    private int todayCaffeineIntakeAmount;
    private int canCaffeineIntakeAmount;
}
