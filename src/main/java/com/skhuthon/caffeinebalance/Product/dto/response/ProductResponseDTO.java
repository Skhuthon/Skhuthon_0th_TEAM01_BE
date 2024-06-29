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

        public static ProductResponseDTO.Brands of(List<String> brands) {
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

        public static ProductResponseDTO.Menu of(List<String> menu) {
            return Menu.builder()
                    .menu(menu)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class Caffeine {
        @Schema(description = "카페인 정보")
        private Double caffeine;

        public static Caffeine of(Double caffeine) {
            return Caffeine.builder()
                    .caffeine(caffeine)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class ProductSearch {
        @Schema(description = "음료 목록")
        private List<Product> products;

        public static ProductSearch from(List<Product> caffeineList) {
            return ProductSearch.builder()
                    .products(caffeineList)
                    .build();
        }
    }
}
