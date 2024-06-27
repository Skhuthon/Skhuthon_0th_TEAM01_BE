package com.skhuthon.caffeinebalance.user.controller;

import com.skhuthon.caffeinebalance.user.dto.request.UserHeightWeightRequestDTO;
import com.skhuthon.caffeinebalance.user.dto.response.UserHeightWeightResponseDTO;
import com.skhuthon.caffeinebalance.user.dto.response.UserResponseDTO;
import com.skhuthon.caffeinebalance.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
@Tag(name = "UserController", description = "마이페이지 관련 API")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "사용자 정보 조회",
            description = "사용자의 정보를 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "요청 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "500", description = "관리자 문의")
            })
    @GetMapping("/getuser")
    public ResponseEntity<UserResponseDTO> getUserInfo() {
        UserResponseDTO userResponseDTO = userService.getUserInfo();
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    @PatchMapping("/update/userinfo")
    @Operation(
            summary = "몸무게와 키를 수정.",
            description = "몸무게와 키를 수정합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "요청 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "500", description = "관리자 문의")
            })
    public ResponseEntity<UserHeightWeightResponseDTO> updateHeightAndWeight(
            @RequestBody UserHeightWeightRequestDTO userHeightWeightRequestDTO) {
        UserHeightWeightResponseDTO userHeightWeightResponseDTO = userService.updateHeightAndWeight(
                userHeightWeightRequestDTO);
        return new ResponseEntity<>(userHeightWeightResponseDTO, HttpStatus.OK);
    }
}
