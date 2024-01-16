package com.socialmeli.SocialMeli.service;

import com.socialmeli.SocialMeli.dto.UserFollowerDTO;

import java.util.Optional;

public interface IUserService {

   UserFollowerDTO follow(Integer idFollower, Integer idFollowed) ;
}
