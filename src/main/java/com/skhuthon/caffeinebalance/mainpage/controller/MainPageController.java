package com.skhuthon.caffeinebalance.mainpage.controller;

import com.skhuthon.caffeinebalance.mainpage.dto.response.MainPageResponseDTO;
import com.skhuthon.caffeinebalance.mainpage.service.MainPageService;
import com.skhuthon.caffeinebalance.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "섭취한 카페인 양 수정.",
            description = "섭취한 카페인 양을 수정합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "요청 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "500", description = "관리자 문의")
            })
    public ResponseEntity<MainPageResponseDTO> getMainPageInfo(@AuthenticationPrincipal User user) {
        MainPageResponseDTO mainPageResponseDTO = mainPageService.getUserCaffeineInfo(user);
        return new ResponseEntity<>(mainPageResponseDTO, HttpStatus.OK);
    }

}
