package com.skhuthon.caffeinebalance.mainpage.dto.response;

import com.skhuthon.caffeinebalance.mainpage.domain.MainPageDrink;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class MainPageDrinkResponseDTO {
    private List<MainPageDrink> recommendedDrinks;
}
