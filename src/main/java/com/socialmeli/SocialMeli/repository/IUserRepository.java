package com.socialmeli.SocialMeli.repository;

import com.socialmeli.SocialMeli.entity.User;

import java.util.List;

public interface IUserRepository extends ICrudRepository<User> {

    User getFollowedUsers(Integer userId,Integer idToFollow);
}
