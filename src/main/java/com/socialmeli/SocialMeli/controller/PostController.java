package com.socialmeli.SocialMeli.controller;

import com.socialmeli.SocialMeli.dto.PostRequestDTO;
import com.socialmeli.SocialMeli.dto.PostResponseDTO;
import com.socialmeli.SocialMeli.exception.BadRequestException;
import com.socialmeli.SocialMeli.service.IPostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    private final IPostService postService;

    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @PostMapping("/products/post")
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO postDTO) {
        if(postDTO.user_id() <= 0 || postDTO.product().product_id() <= 0 || postDTO.category().category_id() <= 0) {
            throw new BadRequestException("Id's must be greater than 0");
        }
        return ResponseEntity.ok().body(postService.createPost(postDTO));
    }
}
