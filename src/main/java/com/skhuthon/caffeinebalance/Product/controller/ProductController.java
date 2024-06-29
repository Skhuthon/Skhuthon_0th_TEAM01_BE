package com.skhuthon.caffeinebalance.Product.controller;

import com.skhuthon.caffeinebalance.Product.dto.request.ProductRequestDTO;
import com.skhuthon.caffeinebalance.Product.dto.request.ProductRequestDTO.KeyWord;
import com.skhuthon.caffeinebalance.Product.dto.response.ProductResponseDTO;
import com.skhuthon.caffeinebalance.Product.dto.response.ProductResponseDTO.ProductSearch;
import com.skhuthon.caffeinebalance.Product.service.ProductService;
import com.skhuthon.caffeinebalance.user.dto.response.UserCaffeineResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Tag(name = "CaffeineController", description = "음료 선택 페이지 관련 API")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    @Operation(
            summary = "상품별 카페인 페이지 / 브랜드 목록 조회",
            description = "브랜드 목록을 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "요청 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "500", description = "관리자 문의")
            })
    public ResponseEntity<ProductResponseDTO.Brands> getBrand() {
        ProductResponseDTO.Brands brands = productService.getBrand();
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @GetMapping("/menu")
    @Operation(
            summary = "브랜드 선택하여 메뉴 조회",
            description = "브랜드 선택하여 해당 브랜드에 해당하는 메뉴를 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "요청 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "500", description = "관리자 문의")
            })
    public ResponseEntity<ProductResponseDTO.Menu> getMenusByBrand(
            @Valid @RequestParam ProductRequestDTO.Brand brand) {
        ProductResponseDTO.Menu menus = productService.getMenuByBrand(brand);
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }

    @GetMapping("/caffeine")
    @Operation(
            summary = "메뉴 선텍하여 카페인 조회",
            description = "메뉴를 선택하여 해당 메뉴의 카페인을 조회합니다",
            responses = {
                    @ApiResponse(responseCode = "200", description = "요청 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "500", description = "관리자 문의")
            })
    public ResponseEntity<ProductResponseDTO.Caffeine> getCaffeineByMenu(
            @Valid @RequestParam ProductRequestDTO.Brand brand,
            @Valid @RequestParam ProductRequestDTO.Menu menu) {
        ProductResponseDTO.Caffeine caffeine = productService.getCaffeineByMenu(brand, menu);
        return new ResponseEntity<>(caffeine, HttpStatus.OK);
    }

    @GetMapping("/search")
    @Operation(
            summary = "검색하기.",
            description = "키워드를 통해 검색하여 음료 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "요청 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "500", description = "관리자 문의")
            })
    public ResponseEntity<ProductSearch> searchCaffeineByKeyword(@Valid @RequestParam KeyWord keyword) {
        ProductSearch caffeineSearchDTO = productService.getMenuSearchList(keyword);
        return new ResponseEntity<>(caffeineSearchDTO, HttpStatus.OK);
    }


    @PatchMapping("/update/caffeine")
    @Operation(
            summary = "카페인 정보 수정.",
            description = "오늘 먹은 카페인 양과 섭취 가능한 카페인 양을 수정합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "요청 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "500", description = "관리자 문의")
            })
    public ResponseEntity<UserCaffeineResponseDTO> updateCaffeineInfo(double caffeine) {
        UserCaffeineResponseDTO userCaffeineResponseDTO = productService.updateTodayCaffeineInformation(caffeine);
        return new ResponseEntity<>(userCaffeineResponseDTO, HttpStatus.OK);
    }
    @GetMapping("/recommend")
    @Operation(
            summary = "추천 음료 조회",
            description = "오늘 먹은 카페인 양과 섭취 가능한 카페인 양을 바탕으로 추천합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "요청 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "500", description = "관리자 문의")
            })
    public ResponseEntity<ProductResponseDTO.RecommendProduct> getRecommendProduct() {
        ProductResponseDTO.RecommendProduct recommendProduct = productService.getRecommendProduct();
        return new ResponseEntity<>(recommendProduct, HttpStatus.OK);
    }

}
