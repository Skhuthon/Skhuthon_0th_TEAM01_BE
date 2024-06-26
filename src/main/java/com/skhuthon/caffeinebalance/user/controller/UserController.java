package com.skhuthon.caffeinebalance.user.controller;

import com.skhuthon.caffeinebalance.user.domain.User;
import com.skhuthon.caffeinebalance.user.dto.request.UserHeightWeightRequestDTO;
import com.skhuthon.caffeinebalance.user.dto.response.UserHeightWeightResponseDTO;
import com.skhuthon.caffeinebalance.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PatchMapping("mypage/heightandweight")
    public ResponseEntity<UserHeightWeightResponseDTO> updateHeightAndWeight(@AuthenticationPrincipal User user, UserHeightWeightRequestDTO userHeightWeightRequestDTO) {
        UserHeightWeightResponseDTO userHeightWeightResponseDTO = userService.updateHeightAndWeight(user, userHeightWeightRequestDTO);
        return new ResponseEntity<>(userHeightWeightResponseDTO, HttpStatus.OK);
    }
}
