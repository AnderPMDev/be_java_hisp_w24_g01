package com.socialmeli.SocialMeli.service;


import com.socialmeli.SocialMeli.dto.UserFollowersCountDTO;

import com.socialmeli.SocialMeli.dto.UserFollowerDTO;

import java.util.Optional;

public interface IUserService {
  UserFollowersCountDTO getFollowersCount(Integer userId);
  Boolean unfollow(Integer userId, Integer userIdToUnfollow);
  UserFollowerDTO follow(Integer idFollower, Integer idFollowed);
}
