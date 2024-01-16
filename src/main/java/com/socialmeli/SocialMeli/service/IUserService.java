package com.socialmeli.SocialMeli.service;

import com.socialmeli.SocialMeli.dto.UserFollowedListDTO;

public interface IUserService {
    boolean userExists(int id);
    UserFollowedListDTO getFollowed(int userId, String order);
}
