package com.skhuthon.caffeinebalance.user.service;

import com.skhuthon.caffeinebalance.global.exception.CustomException;
import com.skhuthon.caffeinebalance.global.exception.ErrorCode;
import com.skhuthon.caffeinebalance.user.domain.User;
import com.skhuthon.caffeinebalance.user.dto.response.UserResponseDTO;
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

    @Transactional(readOnly = true)
    public UserResponseDTO getUserInfo() {
        User user = getCurrentUser();
        return UserResponseDTO.of(user);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new CustomException(ErrorCode.INVALID_USER);
        }
        String username = authentication.getName();
        return userRepository.findByUsername(username);
    }
}
