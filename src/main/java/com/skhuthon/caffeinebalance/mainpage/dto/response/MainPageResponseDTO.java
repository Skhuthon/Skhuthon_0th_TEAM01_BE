package com.skhuthon.caffeinebalance.mainpage.dto.response;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class MainPageResponseDTO {
    private int recommendedCaffeineIntakeAmount;
    private int todayCaffeineIntakeAmount;
    private int canCaffeineIntakeAmount;
    private MainPageDrinkResponseDTO mainPageDrinkResponseDTO;
}
