package com.socialmeli.SocialMeli.services;

import com.socialmeli.SocialMeli.dto.LastestPostDTO;

public interface ILastestPostService {
    LastestPostDTO getLastestPost(Integer userId, String order);
}
