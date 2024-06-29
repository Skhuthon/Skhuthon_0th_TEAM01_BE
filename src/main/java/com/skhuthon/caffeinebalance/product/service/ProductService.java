package com.skhuthon.caffeinebalance.Product.service;

import com.skhuthon.caffeinebalance.Product.domain.Product;
import com.skhuthon.caffeinebalance.Product.dto.request.ProductRequestDTO;
import com.skhuthon.caffeinebalance.Product.dto.response.ProductResponseDTO;
import com.skhuthon.caffeinebalance.Product.dto.response.ProductResponseDTO.Products;
import com.skhuthon.caffeinebalance.Product.repository.ProductRepository;
import com.skhuthon.caffeinebalance.exception.CustomException;
import com.skhuthon.caffeinebalance.exception.ErrorCode;
import com.skhuthon.caffeinebalance.user.domain.User;
import com.skhuthon.caffeinebalance.user.dto.response.UserCaffeineResponseDTO;
import com.skhuthon.caffeinebalance.user.repository.UserRepository;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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

    public UserCaffeineResponseDTO updateTodayCaffeineInformation(double caffeine) {
        User user = getCurrentUser();
        user.updateCaffeineInformation(caffeine);

        return UserCaffeineResponseDTO.of(user);
    }

    public ProductResponseDTO.RecommendProduct getRecommendProduct() {
        User user = getCurrentUser();
        List<Product> products = productRepository.findRandomCaffeineByCanCaffeineIntakeAmount(user.getCanCaffeineIntakeAmount()).orElseThrow(
                () -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
        System.out.println(products.size());
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
