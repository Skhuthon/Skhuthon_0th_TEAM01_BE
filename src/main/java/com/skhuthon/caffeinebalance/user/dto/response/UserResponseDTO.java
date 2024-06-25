package com.skhuthon.caffeinebalance.user.dto.response;

import com.skhuthon.caffeinebalance.user.domain.User;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDTO {
    @NotNull(message = "username은 null이 될 수 없습니다.")
    private String username;

    @NotNull(message = "name은 null이 될 수 없습니다.")
    private String name;

    @NotNull(message = "email은 null이 될 수 없습니다.")
    private String email;;

    @NotNull(message = "profile은 null이 될 수 없습니다.")
    private String profile;

    @NotNull(message = "role은 null이 될 수 없습니다.")
    private String role;

    public static UserResponseDTO fromEntity(User user) {
        return UserResponseDTO.builder()
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .profile(user.getProfile())
                .role(user.getRole().name())
                .build();
    }
    public static UserResponseDTO createFromJwt(String username, String role) {
        return UserResponseDTO.builder()
                .username(username)
                .name(username) // JWT에서 이름을 가져올 수 없는 경우, 사용자 이름을 대신 사용할 수 있음
                .role(role)
                .build();
    }
}
