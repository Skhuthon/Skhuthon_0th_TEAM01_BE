package com.skhuthon.caffeinebalance.user.service;

import com.skhuthon.caffeinebalance.user.domain.User;
import com.skhuthon.caffeinebalance.user.dto.request.UserHeightWeightRequestDTO;
import com.skhuthon.caffeinebalance.user.dto.response.UserHeightWeightResponseDTO;
import com.skhuthon.caffeinebalance.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserHeightWeightResponseDTO updateHeightAndWeight(User user, UserHeightWeightRequestDTO userHeightWeightRequestDTO) {
        user.updateHeightAndWeight(userHeightWeightRequestDTO.getHeight(), userHeightWeightRequestDTO.getWeight());
        return UserHeightWeightResponseDTO.from(user);
    }
}
