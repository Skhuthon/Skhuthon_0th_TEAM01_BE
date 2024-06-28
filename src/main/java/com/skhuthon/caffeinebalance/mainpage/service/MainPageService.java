package com.skhuthon.caffeinebalance.mainpage.service;

import com.skhuthon.caffeinebalance.mainpage.domain.MainPageDrink;
import com.skhuthon.caffeinebalance.mainpage.dto.response.MainPageDrinkResponseDTO;
import com.skhuthon.caffeinebalance.mainpage.dto.response.MainPageResponseDTO;
import com.skhuthon.caffeinebalance.mainpage.repository.MainPageCaffeineIntakeRepository;
import com.skhuthon.caffeinebalance.user.domain.User;
import com.skhuthon.caffeinebalance.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainPageService {

    private final MainPageCaffeineIntakeRepository mainPageCaffeineIntakeRepository;
    private final UserRepository userRepository;
    private final MainPageDrinkService mainPageDrinkService;

    public MainPageResponseDTO getUserCaffeineInfo(User user) {
        int recommendedCaffeineIntakeAmount = user.getRecommendedCaffeineIntakeAmount();
        int todayCaffeineIntakeAmount = user.getTodayCaffeineIntakeAmount();
        int canCaffeineIntakeAmount = recommendedCaffeineIntakeAmount - todayCaffeineIntakeAmount;

        List<MainPageDrink> recommendedDrinks = mainPageDrinkService.recommendDrinks(canCaffeineIntakeAmount);
        MainPageDrinkResponseDTO mainPageDrinkResponseDTO = MainPageDrinkResponseDTO.builder()
                .recommendedDrinks(recommendedDrinks)
                .build();

        return MainPageResponseDTO.builder()
                .recommendedCaffeineIntakeAmount(recommendedCaffeineIntakeAmount)
                .todayCaffeineIntakeAmount(todayCaffeineIntakeAmount)
                .canCaffeineIntakeAmount(canCaffeineIntakeAmount)
                .mainPageDrinkResponseDTO(mainPageDrinkResponseDTO)
                .build();
    }
}
