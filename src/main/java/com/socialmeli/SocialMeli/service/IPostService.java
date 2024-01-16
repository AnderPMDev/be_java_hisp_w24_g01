package com.socialmeli.SocialMeli.service;

import com.socialmeli.SocialMeli.dto.PostRequestDTO;
import com.socialmeli.SocialMeli.dto.PostResponseDTO;

public interface IPostService {
    PostResponseDTO createPost(PostRequestDTO postDTO);
}
