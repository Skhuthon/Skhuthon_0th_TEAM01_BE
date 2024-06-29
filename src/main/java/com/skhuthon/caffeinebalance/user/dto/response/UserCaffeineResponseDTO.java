package com.skhuthon.caffeinebalance.user.dto.response;


import com.skhuthon.caffeinebalance.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserCaffeineResponseDTO {
    private double todayCaffeineIntakeAmount;
    private double canCaffeineIntakeAmount;
    private double  dailyRecommendedCaffeineAmount;

    public static UserCaffeineResponseDTO of(User user) {
        return UserCaffeineResponseDTO.builder()
                .todayCaffeineIntakeAmount(user.getTodayCaffeineIntakeAmount())
                .canCaffeineIntakeAmount(user.getCanCaffeineIntakeAmount())
                .dailyRecommendedCaffeineAmount(user.getDailyRecommendedCaffeineAmount())
                .build();
    }
}
