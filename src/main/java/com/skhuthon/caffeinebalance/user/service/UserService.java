package com.skhuthon.caffeinebalance.user.service;

import com.skhuthon.caffeinebalance.user.domain.User;
import com.skhuthon.caffeinebalance.user.dto.request.UserHeightWeightRequestDTO;
import com.skhuthon.caffeinebalance.user.dto.response.UserHeightWeightResponseDTO;
import com.skhuthon.caffeinebalance.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserHeightWeightResponseDTO updateHeightAndWeight(UserHeightWeightRequestDTO userHeightWeightRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalArgumentException("인증되지 않은 사용자입니다.");
        }

        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        user.updateHeightAndWeight(userHeightWeightRequestDTO.height(), userHeightWeightRequestDTO.weight());

        return UserHeightWeightResponseDTO.from(user);
    }
}
