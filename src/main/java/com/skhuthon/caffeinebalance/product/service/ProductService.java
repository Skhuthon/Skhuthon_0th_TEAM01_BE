package com.skhuthon.caffeinebalance.product.service;

import com.skhuthon.caffeinebalance.product.domain.Product;
import com.skhuthon.caffeinebalance.product.dto.request.ProductRequestDTO;
import com.skhuthon.caffeinebalance.product.dto.response.ProductResponseDTO;
import com.skhuthon.caffeinebalance.product.dto.response.ProductResponseDTO.Products;
import com.skhuthon.caffeinebalance.product.repository.ProductRepository;
import com.skhuthon.caffeinebalance.global.exception.CustomException;
import com.skhuthon.caffeinebalance.global.exception.ErrorCode;
import com.skhuthon.caffeinebalance.user.domain.User;
import com.skhuthon.caffeinebalance.user.dto.response.UserCaffeineResponseDTO;
import com.skhuthon.caffeinebalance.user.repository.UserRepository;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductResponseDTO.Brands getBrand() {
        List<String> brands = productRepository.getBrands().orElseThrow(
                () -> new CustomException(ErrorCode.BRAND_NOT_FOUND));
        return ProductResponseDTO.Brands.from(brands);
    }

    public ProductResponseDTO.Menu getMenuByBrand(ProductRequestDTO.Brand brand) {
        List<String> menu = productRepository.findMenuByBrand(brand.getBrand()).orElseThrow(
                () -> new CustomException(ErrorCode.BRAND_NOT_FOUND));
        return ProductResponseDTO.Menu.from(menu);
    }

    public ProductResponseDTO.Caffeine getCaffeineByMenu(ProductRequestDTO.Brand brand, ProductRequestDTO.Menu menu) {
        Double caffeine = productRepository.findCaffeineByMenu(brand.getBrand(), menu.getMenu()).orElseThrow(
                () -> new CustomException(ErrorCode.BRAND_AND_MENU_NOT_FOUND));
        return ProductResponseDTO.Caffeine.from(caffeine);
    }

    public Products getMenuSearchList(ProductRequestDTO.KeyWord keyword) {
        List<Product> caffeineList = productRepository.findByMenuContaining(keyword.getKeyword());
        return Products.from(caffeineList);
    }

    @Transactional
    public UserCaffeineResponseDTO updateTodayCaffeineInformation(double caffeine) {
        User user = getCurrentUser();
        double canCaffeineIntakeAmount = user.getDailyRecommendedCaffeineAmount() - caffeine;
        if (canCaffeineIntakeAmount < 0D) {
            throw new CustomException(ErrorCode.CAFFEINE_CANNOT_BE_NEGATIVE);
        }

        user.updateCaffeineInformation(caffeine, canCaffeineIntakeAmount);

        return UserCaffeineResponseDTO.of(user);
    }

    public ProductResponseDTO.RecommendProduct getRecommendProduct() {
        User user = getCurrentUser();
        List<Product> products = productRepository.findRandomCaffeineByCanCaffeineIntakeAmount(user.getCanCaffeineIntakeAmount()).orElseThrow(
                () -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
        Collections.shuffle(products);
        Product product = products.get(0);

        return ProductResponseDTO.RecommendProduct.from(product);
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
