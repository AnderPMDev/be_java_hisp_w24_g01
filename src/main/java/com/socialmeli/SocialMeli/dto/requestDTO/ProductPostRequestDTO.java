package com.socialmeli.SocialMeli.dto.requestDTO;

import jakarta.validation.constraints.*;

public record ProductPostRequestDTO(

        @Min(value = 1, message = "The id must be greater than zero")
        @NotNull(message = "The id cannot be empty.")
        Integer product_id,
        @NotEmpty(message = "The field product name cannot be empty")
        @Size(max = 40, message = "The length product name  cannot exceed 40 characters.")
        @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "The field cannot contain special characters.")
        String product_name,
        @NotEmpty(message = "The field type cannot be empty")
        @Size(max = 15, message = "The length tyoe cannot exceed 15 characters.")
        @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "The field cannot contain special characters.")
        String type,
        @NotEmpty(message = "The field brand cannot be empty")
        @Size(max = 25, message = "The length brand cannot exceed 25 characters.")
        @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "The field cannot contain special characters.")
        String brand,
        @NotEmpty(message = "The field color cannot be empty")
        @Size(max = 15, message = "The length color cannot exceed 15 characters.")
        @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "The field cannot contain special characters.")
        String color,


        @Size(max = 80, message = "The length notes cannot exceed 80 characters.")
        @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "The field cannot contain special characters.")
        String notes
) {

}
