package com.skhuthon.caffeinebalance.Product.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProductRequestDTO {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Brand {
        @Schema(description = "브랜드")
        @NotNull(message = "브랜드는 null이 될 수 없습니다")
        private String brand;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Menu {
        @Schema(description = "메뉴")
        @NotNull(message = "메뉴는 null이 될 수 없습니다")
        private String Menu;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class KeyWord {
        @Schema(description = "검색 키워드")
        @NotNull(message = "검색 키워드는 null이 될 수 없습니다")
        private String keyword;
    }
}
