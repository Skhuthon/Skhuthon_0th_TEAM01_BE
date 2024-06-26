package com.skhuthon.caffeinebalance.user.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserHeightWeightRequestDTO(@NotBlank double height, @NotBlank double weight) {
}
