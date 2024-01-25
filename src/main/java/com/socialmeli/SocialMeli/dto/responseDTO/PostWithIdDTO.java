package com.socialmeli.SocialMeli.dto.responseDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.socialmeli.SocialMeli.dto.requestDTO.CategoryPostRequestDTO;
import com.socialmeli.SocialMeli.dto.requestDTO.ProductPostRequestDTO;

import java.time.LocalDate;

public record PostWithIdDTO(
        Integer user_id,
        Integer post_id,
        LocalDate date,
        ProductPostRequestDTO product,
        CategoryPostRequestDTO category,
        Double price
) {
}
