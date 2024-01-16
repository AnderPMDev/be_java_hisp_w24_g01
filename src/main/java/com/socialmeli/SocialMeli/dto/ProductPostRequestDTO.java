package com.socialmeli.SocialMeli.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPostRequestDTO(
        int product_id,
        String product_name,
        String type,
        String brand,
        String color,
        String notes
) {
}
