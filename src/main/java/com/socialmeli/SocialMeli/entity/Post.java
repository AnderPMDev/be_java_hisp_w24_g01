package com.socialmeli.SocialMeli.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Post {
    private Integer id;
    private Integer userId;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    private Product product;
    private Category category;
    private Double price;
}

