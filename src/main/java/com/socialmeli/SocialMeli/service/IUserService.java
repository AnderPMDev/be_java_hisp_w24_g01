package com.socialmeli.SocialMeli.service;


import com.socialmeli.SocialMeli.dto.UserDTO;
import com.socialmeli.SocialMeli.dto.UserFollowersCountDTO;
import com.socialmeli.SocialMeli.dto.UserFollowerDTO;
import com.socialmeli.SocialMeli.dto.UserFollowersDTO;
import com.socialmeli.SocialMeli.dto.UserFollowedListDTO;

import java.util.Optional;

public interface IUserService {
  UserFollowersCountDTO getFollowersCount(Integer userId);
  Boolean unfollow(Integer userId, Integer userIdToUnfollow);
  UserFollowerDTO follow(Integer idFollower, Integer idFollowed);

  UserFollowersDTO getUserWithFollowers(Integer id, String order);

  boolean userExists(int id);
  UserFollowedListDTO getFollowed(int userId, String order);
}
