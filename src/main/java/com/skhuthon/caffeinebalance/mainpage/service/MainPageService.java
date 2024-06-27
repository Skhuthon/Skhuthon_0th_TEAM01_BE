package com.skhuthon.caffeinebalance.mainpage.service;

import com.skhuthon.caffeinebalance.mainpage.repository.MainPageCaffeineIntakeRepository;
import com.skhuthon.caffeinebalance.user.domain.User;
import com.skhuthon.caffeinebalance.user.repository.UserRepository;
import com.skhuthon.caffeinebalance.mainpage.dto.response.MainPageResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MainPageService {

    private final MainPageCaffeineIntakeRepository mainPageCaffeineIntakeRepository;
    private final UserRepository userRepository;

    public MainPageResponseDTO getUserCaffeineInfo(User user) {
        int recommendedCaffeineIntakeAmount = user.getRecommendedCaffeineIntakeAmount();
        int todayCaffeineIntakeAmount = user.getTodayCaffeineIntakeAmount();
        int canCaffeineIntakeAmount = recommendedCaffeineIntakeAmount - todayCaffeineIntakeAmount;

        return MainPageResponseDTO.builder()
                .recommendedCaffeineIntakeAmount(recommendedCaffeineIntakeAmount)
                .todayCaffeineIntakeAmount(todayCaffeineIntakeAmount)
                .canCaffeineIntakeAmount(canCaffeineIntakeAmount)
                .build();
    }

    @Transactional
    public void updateTodayCaffeineIntake(User user, int todayCaffeineIntakeAmount) {
        user.setTodayCaffeineIntakeAmount(todayCaffeineIntakeAmount);
    }
}
