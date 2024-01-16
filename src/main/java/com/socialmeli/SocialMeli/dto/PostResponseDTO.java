package com.socialmeli.SocialMeli.dto;

public record PostResponseDTO(
        int id,
        int user_id,
        String date,
        int product_id,
        String product_name,
        int category_id,
        String category_name,
        double price
) {
}
