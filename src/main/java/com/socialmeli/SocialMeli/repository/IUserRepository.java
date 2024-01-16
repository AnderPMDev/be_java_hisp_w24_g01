package com.socialmeli.SocialMeli.repository;

import com.socialmeli.SocialMeli.entity.User;

import java.util.Optional;

public interface IUserRepository extends ICrudRepository<User> {

    boolean userExists(int id);
}
