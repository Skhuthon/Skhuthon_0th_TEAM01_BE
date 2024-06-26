package com.skhuthon.caffeinebalance.user.dto.response;

import com.skhuthon.caffeinebalance.user.domain.User;
import com.skhuthon.caffeinebalance.user.dto.request.UserHeightWeightRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserHeightWeightResponseDTO {
    private double height;
    private double weight;

    public static UserHeightWeightResponseDTO from(User user) {
        return new UserHeightWeightResponseDTO(user.getHeight(), user.getWeight());
    }
}
