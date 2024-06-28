package com.skhuthon.caffeinebalance.user.dto.response;

import com.skhuthon.caffeinebalance.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private String name;
    private String email;
    private String profile;
    private String role;
    private int recommendedCaffeineIntakeAmount;
    private int todayCaffeineIntakeAmount;
    private int canCaffeineIntakeAmount;
    private double height;
    private double weight;

    public static UserResponseDTO of(User user) {
        return UserResponseDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .profile(user.getProfile())
                .role(user.getRole().name())
                .recommendedCaffeineIntakeAmount(user.getRecommendedCaffeineIntakeAmount())
                .todayCaffeineIntakeAmount(user.getTodayCaffeineIntakeAmount())
                .canCaffeineIntakeAmount(user.getCanCaffeineIntakeAmount())
                .height(user.getHeight())
                .weight(user.getWeight())
                .build();
    }
}
