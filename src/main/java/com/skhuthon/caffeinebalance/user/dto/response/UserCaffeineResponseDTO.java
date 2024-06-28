package com.skhuthon.caffeinebalance.user.dto.response;


import com.skhuthon.caffeinebalance.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserCaffeineResponseDTO {
    private Double todayCaffeineIntakeAmount;
    private Double canCaffeineIntakeAmount;

    public static UserCaffeineResponseDTO of(User user) {
        return UserCaffeineResponseDTO.builder()
                .todayCaffeineIntakeAmount(user.getTodayCaffeineIntakeAmount())
                .canCaffeineIntakeAmount(user.getCanCaffeineIntakeAmount())
                .build();
    }
}
