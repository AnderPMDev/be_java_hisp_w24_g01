package com.socialmeli.SocialMeli.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.socialmeli.SocialMeli.entity.Category;
import com.socialmeli.SocialMeli.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostWithIdDTO {
    private Integer userId;
    private Integer postId;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING, timezone="EST")
    private LocalDate date;
    private Product product = null;
    private Category category;
    private Double price;
}
