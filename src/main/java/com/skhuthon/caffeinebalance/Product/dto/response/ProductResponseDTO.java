package com.skhuthon.caffeinebalance.Product.dto.response;

import com.skhuthon.caffeinebalance.Product.domain.Product;
import io.swagger.v3.oas.annotations.media.Schema;
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
        @Schema(description = "카페인 정보")
        private double caffeine;

        public static ProductResponseDTO.Caffeine from(Double caffeine) {
            return Caffeine.builder()
                    .caffeine(caffeine)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class Products {
        @Schema(description = "음료 목록")
        private List<Product> products;

        public static ProductResponseDTO.Products from(List<Product> caffeineList) {
            return Products.builder()
                    .products(caffeineList)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class RecommendProduct {
        private String brand;
        private String menu;
        private double caffeine;

        public static ProductResponseDTO.RecommendProduct from(Product product) {
            return RecommendProduct.builder()
                    .brand(product.getBrand())
                    .menu(product.getMenu())
                    .caffeine(product.getCaffeine())
                    .build();
        }
    }
}
