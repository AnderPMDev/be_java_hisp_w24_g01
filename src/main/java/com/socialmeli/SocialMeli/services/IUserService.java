package com.socialmeli.SocialMeli.services;

import com.socialmeli.SocialMeli.dto.UserDTO;

import java.util.List;

public interface IUserService {
    UserDTO getUserWithFollowers(Integer id, String order);
}
