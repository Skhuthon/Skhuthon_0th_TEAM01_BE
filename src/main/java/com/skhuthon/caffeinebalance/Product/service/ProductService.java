package com.skhuthon.caffeinebalance.caffeine.service;

import com.skhuthon.caffeinebalance.caffeine.domain.Product;
import com.skhuthon.caffeinebalance.caffeine.dto.request.ProductRequestDTO;
import com.skhuthon.caffeinebalance.caffeine.dto.response.ProductResponseDTO;
import com.skhuthon.caffeinebalance.caffeine.dto.response.ProductResponseDTO.ProductSearch;
import com.skhuthon.caffeinebalance.caffeine.repository.ProductRepository;
import com.skhuthon.caffeinebalance.exception.CustomException;
import com.skhuthon.caffeinebalance.exception.ErrorCode;
import com.skhuthon.caffeinebalance.user.domain.User;
import com.skhuthon.caffeinebalance.user.dto.response.UserCaffeineResponseDTO;
import com.skhuthon.caffeinebalance.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository coffeeRepository;
    private final UserRepository userRepository;

    public ProductResponseDTO.Brands getBrand() {
        List<String> brands = coffeeRepository.getBrands().orElseThrow(
                () -> new CustomException(ErrorCode.BRAND_NOT_FOUND));
        return ProductResponseDTO.Brands.of(brands);
    }

    public ProductResponseDTO.Menu getMenuByBrand(ProductRequestDTO.Brand brand) {
        List<String> menu = coffeeRepository.findMenuByBrand(brand.getBrand()).orElseThrow(
                () -> new CustomException(ErrorCode.BRAND_NOT_FOUND));
        return ProductResponseDTO.Menu.of(menu);
    }

    public ProductResponseDTO.Caffeine getCaffeineByMenu(ProductRequestDTO.Brand brand, ProductRequestDTO.Menu menu) {
        Double caffeine = coffeeRepository.findCaffeineByMenu(brand.getBrand(), menu.getMenu()).orElseThrow(
                () -> new CustomException(ErrorCode.BRAND_AND_MENU_NOT_FOUND));
        return ProductResponseDTO.Caffeine.of(caffeine);
    }

    public ProductResponseDTO.ProductSearch getMenuSearchList(ProductRequestDTO.KeyWord keyword) {
        List<Product> caffeineList = coffeeRepository.findByMenuContaining(keyword.getKeyword());
        return ProductSearch.from(caffeineList);
    }

    public UserCaffeineResponseDTO updateTodayCaffeineInformation(double caffeine) {
        User user = getCurrentUser();
        double recommendedCaffeineIntakeAmount = 400D;
        double canCaffeineIntakeAmount = recommendedCaffeineIntakeAmount - caffeine;
        user.updateCaffeineInformation(caffeine, canCaffeineIntakeAmount);

        return UserCaffeineResponseDTO.of(user);
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
