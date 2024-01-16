package com.socialmeli.SocialMeli.service;

import com.socialmeli.SocialMeli.dto.UserFollowersCountDTO;

public interface IUserService {
    UserFollowersCountDTO getFollowersCount(Integer userId);

    Boolean unfollow(Integer userId, Integer userIdToUnfollow);
}
