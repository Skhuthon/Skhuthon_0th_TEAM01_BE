package com.skhuthon.caffeinebalance.user.dto.response;

import com.skhuthon.caffeinebalance.user.domain.User;
import com.skhuthon.caffeinebalance.user.dto.request.UserHeightWeightRequestDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserHeightWeightResponseDTO {
    private Double height;
    private Double weight;

    public static UserHeightWeightResponseDTO from(User user) {
        return new UserHeightWeightResponseDTO(user.getHeight(), user.getWeight());
    }
}
