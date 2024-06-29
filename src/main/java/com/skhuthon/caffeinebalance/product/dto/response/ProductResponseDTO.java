package com.skhuthon.caffeinebalance.product.dto.response;

import com.skhuthon.caffeinebalance.product.domain.Product;
import com.skhuthon.caffeinebalance.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

public class ProductResponseDTO {
    @Getter
    @Builder
    public static class Brands {
        @Schema(description = "브랜드 목록")
        private List<String> brands;

        public static ProductResponseDTO.Brands from(List<String> brands) {
            return Brands.builder()
                    .brands(brands)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class Menu {
        @Schema(description = "메뉴 목록")
        private List<String> menu;

        public static ProductResponseDTO.Menu from(List<String> menu) {
            return Menu.builder()
                    .menu(menu)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class Caffeine {
        @Schema(description = "id 정보")
        private Long id;

        @Schema(description = "카페인 정보")
        private double caffeine;

        public static ProductResponseDTO.Caffeine from(Product product) {
            return Caffeine.builder()
                    .id(product.getId())
                    .caffeine(product.getCaffeine())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class Products {
        @Schema(description = "id 정보")
        private Long id;

        @Schema(description = "브랜드")
        private String brand;

        @Schema(description = "메뉴")
        private String menu;

        @Schema(description = "카페인")
        private double caffeine;

        public static ProductResponseDTO.Products from(Product product) {
            return Products.builder()
                    .id(product.getId())
                    .brand(product.getBrand())
                    .menu(product.getMenu())
                    .caffeine(product.getCaffeine())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class ProductInfo {
        private String brand;
        private String menu;
        private double caffeine;

        public static ProductInfo from(Product product) {
            return ProductInfo.builder()
                    .brand(product.getBrand())
                    .menu(product.getMenu())
                    .caffeine(product.getCaffeine())
                    .build();
        }
    }
}
