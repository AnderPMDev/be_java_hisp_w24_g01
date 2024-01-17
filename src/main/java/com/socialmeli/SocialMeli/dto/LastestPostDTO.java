package com.socialmeli.SocialMeli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LastestPostDTO {
    private Integer userId;
    private List<PostWithIdDTO> post;
}
