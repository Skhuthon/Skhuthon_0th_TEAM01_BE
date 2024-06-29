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
        List<String> brands = fetchBrands();
        return ProductResponseDTO.Brands.from(brands);
    }

    public ProductResponseDTO.Menu getMenuByBrand(ProductRequestDTO.Brand brand) {
        List<String> menu = fetchMenuByBrand(brand);
        return ProductResponseDTO.Menu.from(menu);
    }

    public ProductResponseDTO.Caffeine getCaffeineByMenu(ProductRequestDTO.Brand brand, ProductRequestDTO.Menu menu) {
        Double caffeine = fetchCaffeineByMenu(brand, menu);
        return ProductResponseDTO.Caffeine.from(caffeine);
    }

    public Products getMenuSearchList(ProductRequestDTO.KeyWord keyword) {
        List<Product> caffeineList = fetchMenuSearchList(keyword);
        return Products.from(caffeineList);
    }

    @Transactional
    public UserCaffeineResponseDTO updateTodayCaffeineInformation(Long productId) {
        Product product = fetchProductById(productId);
        User user = fetchCurrentUser();

        double canCaffeineIntakeAmount = user.getDailyRecommendedCaffeineAmount() - product.getCaffeine();
        if (canCaffeineIntakeAmount < 0D) {
            throw new CustomException(ErrorCode.CAFFEINE_CANNOT_BE_NEGATIVE);
        }

        user.getProducts().add(product);
        user.updateCaffeineInformation(product.getCaffeine(), canCaffeineIntakeAmount);

        return UserCaffeineResponseDTO.of(user);
    }

    public ProductResponseDTO.RecommendProduct getRecommendProduct() {
        User user = fetchCurrentUser();
        List<Product> products = fetchRecommendProduct(user.getCanCaffeineIntakeAmount());
        Collections.shuffle(products);
        Product product = products.get(0);

        return ProductResponseDTO.RecommendProduct.from(product);
    }

    private List<String> fetchBrands() {
        return productRepository.getBrands().orElseThrow(
                () -> new CustomException(ErrorCode.BRAND_NOT_FOUND));
    }

    private List<String> fetchMenuByBrand(ProductRequestDTO.Brand brand) {
        return productRepository.findMenuByBrand(brand.getBrand()).orElseThrow(
                () -> new CustomException(ErrorCode.BRAND_NOT_FOUND));
    }

    private Double fetchCaffeineByMenu(ProductRequestDTO.Brand brand, ProductRequestDTO.Menu menu) {
        return productRepository.findCaffeineByMenu(brand.getBrand(), menu.getMenu()).orElseThrow(
                () -> new CustomException(ErrorCode.BRAND_AND_MENU_NOT_FOUND));
    }

    private List<Product> fetchMenuSearchList(ProductRequestDTO.KeyWord keyword) {
        return productRepository.findByMenuContaining(keyword.getKeyword());
    }

    private Product fetchProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(
                () -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
    }

    private List<Product> fetchRecommendProduct(double canCaffeineIntakeAmount) {
        return productRepository.findRandomCaffeineByCanCaffeineIntakeAmount(canCaffeineIntakeAmount).orElseThrow(
                () -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
    }

    private User fetchCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new CustomException(ErrorCode.INVALID_USER);
        }
        String username = authentication.getName();
        return userRepository.findByUsername(username);
    }
}
