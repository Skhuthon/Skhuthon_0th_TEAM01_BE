package com.skhuthon.caffeinebalance.mainpage.controller;

import com.skhuthon.caffeinebalance.mainpage.dto.response.MainPageResponseDTO;
import com.skhuthon.caffeinebalance.mainpage.service.MainPageService;
import com.skhuthon.caffeinebalance.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainPageController {
    private final MainPageService mainPageService;

    @GetMapping
    public ResponseEntity<MainPageResponseDTO> getMainPageInfo(@AuthenticationPrincipal User user) {
        MainPageResponseDTO mainPageResponseDTO = mainPageService.getUserCaffeineInfo(user);
        return new ResponseEntity<>(mainPageResponseDTO, HttpStatus.OK);
    }
}
