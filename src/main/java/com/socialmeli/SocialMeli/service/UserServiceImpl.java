package com.socialmeli.SocialMeli.service;

import com.socialmeli.SocialMeli.dto.UserFollowerDTO;
import com.socialmeli.SocialMeli.entity.User;
import com.socialmeli.SocialMeli.exception.BadRequest;
import com.socialmeli.SocialMeli.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {


    IUserRepository userRepository;
    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserFollowerDTO follow(Integer idFollower, Integer idFollowed) {
        var user = userRepository.getFollowedUsers(idFollower, idFollowed);
        return new UserFollowerDTO(user.getId(), user.getName(), user.getFollowed());

    }
}
