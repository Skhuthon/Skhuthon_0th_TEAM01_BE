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
    private String birthday;
    private String role;
    private Double todayCaffeineIntakeAmount;
    private Double canCaffeineIntakeAmount;


    public static UserResponseDTO of(User user) {
        return UserResponseDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .profile(user.getProfile())
                .birthday(user.getBirthday())
                .role(user.getRole().name())
                .todayCaffeineIntakeAmount(user.getTodayCaffeineIntakeAmount())
                .canCaffeineIntakeAmount(user.getCanCaffeineIntakeAmount())
                .build();
    }
}
