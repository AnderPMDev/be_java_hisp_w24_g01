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
    private int id;
    private int userId;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    private Product product;
    private Category category;
    private double price;
}

