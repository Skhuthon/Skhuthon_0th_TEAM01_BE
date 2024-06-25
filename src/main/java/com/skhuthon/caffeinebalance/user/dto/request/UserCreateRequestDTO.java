package com.skhuthon.caffeinebalance.user.dto.request;

import com.skhuthon.caffeinebalance.auth.dto.OAuth2Response;
import com.skhuthon.caffeinebalance.user.domain.Role;
import com.skhuthon.caffeinebalance.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserCreateRequestDTO {
    private String username;
    private String name;
    private String email;
    private String profile;
    private int recommendedCaffeineIntakeAmount;
    private int todayCaffeineIntakeAmount;
    private int canCaffeineIntakeAmount;

    public User toEntity() {
        return User.builder()
                .username(username)
                .name(name)
                .email(email)
                .role(Role.ROLE_USER)
                .profile(profile)
                .recommendedCaffeineIntakeAmount(recommendedCaffeineIntakeAmount)
                .todayCaffeineIntakeAmount(todayCaffeineIntakeAmount)
                .canCaffeineIntakeAmount(canCaffeineIntakeAmount)
                .build();
    }

    public static UserCreateRequestDTO fromOAuth2Response(OAuth2Response oAuth2Response, String username) {
        return UserCreateRequestDTO.builder()
                .username(username)
                .name(oAuth2Response.getName())
                .email(oAuth2Response.getEmail())
                .profile(oAuth2Response.getProfileImage())
                .recommendedCaffeineIntakeAmount(0)
                .todayCaffeineIntakeAmount(0)
                .canCaffeineIntakeAmount(0)
                .build();
    }
}
