package com.skhuthon.caffeinebalance.user.controller;

import com.skhuthon.caffeinebalance.user.domain.User;
import com.skhuthon.caffeinebalance.user.dto.request.UserHeightWeightRequestDTO;
import com.skhuthon.caffeinebalance.user.dto.response.UserHeightWeightResponseDTO;
import com.skhuthon.caffeinebalance.user.repository.UserRepository;
import com.skhuthon.caffeinebalance.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class UserController {

    private final UserService userService;

    @PatchMapping( "/heightandweight")
    public ResponseEntity<UserHeightWeightResponseDTO> updateHeightAndWeight(@RequestBody UserHeightWeightRequestDTO userHeightWeightRequestDTO) {
        UserHeightWeightResponseDTO userHeightWeightResponseDTO = userService.updateHeightAndWeight(userHeightWeightRequestDTO);
        return new ResponseEntity<>(userHeightWeightResponseDTO, HttpStatus.OK);
    }
}
