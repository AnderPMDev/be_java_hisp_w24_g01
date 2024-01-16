package com.socialmeli.SocialMeli.service;

import com.socialmeli.SocialMeli.dto.UserFollowersCountDTO;
import com.socialmeli.SocialMeli.entity.User;
import com.socialmeli.SocialMeli.exception.UserNotFollowedException;
import com.socialmeli.SocialMeli.exception.UserNotFoundException;
import com.socialmeli.SocialMeli.repository.IUserRepository;
import org.springframework.stereotype.Service;

import com.socialmeli.SocialMeli.dto.UserDTO;
import com.socialmeli.SocialMeli.dto.UserFollowerDTO;
import com.socialmeli.SocialMeli.entity.User;
import com.socialmeli.SocialMeli.exception.BadRequest;
import com.socialmeli.SocialMeli.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    /*
    *   Autowired should not be used here
    *   The repository should be injected in the constructor
    */
    private IUserRepository userRepository;
    public UserService(IUserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserFollowersCountDTO getFollowersCount(Integer userId) {
        //Get the ammount of users that follow a certain vendor
        Long count = 0L;
        Integer followersCount = 0;

        //Get the list of followers of the user and count it
        count = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"))
            .getFollowers().stream().count();

        //Cast the Long to Integer
        followersCount = Integer.parseInt(count.toString());

        //Return the DTO
        return new UserFollowersCountDTO(userId, userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found")).getName(), followersCount);

        /*
        * The cast should be deleted
        * Retrieving the user two times is not efficient
        */
    }

    @Override
    public Boolean unfollow(Integer userId, Integer userIdToUnfollow) {
        //Unfollow a user given its ID
        //If the user is not found, return false
        //If the user is found, remove it from the list of followers and return true
        Boolean unfollowed = false;

        //Search for the user and the user to unfollow
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        User userToUnfollow = userRepository.findById(userIdToUnfollow).orElseThrow(() -> new UserNotFoundException("User not found"));
        //Check if the user to unfollow is in the list of followers of the user
        if (user.getFollowed().contains(userToUnfollow)) {
            user.getFollowed().remove(userToUnfollow);
            unfollowed = true; //Return true if everything went well
        }
        else{
            System.out.println(user.getId().toString());
            System.out.println(user.getFollowed().toString());
            System.out.println("User not followed");
            System.out.println(userId);
            System.out.println(userIdToUnfollow);
            throw new UserNotFollowedException("User not followed");
        }
        return unfollowed; 
      //If the user was not found, return false
    }

    @Override
    public UserFollowerDTO follow(Integer idFollower, Integer idFollowed) {
        var user = userRepository.getFollowedUsers(idFollower, idFollowed);
        List<UserDTO> followedbyuser = user.getFollowed().stream().map(u -> new UserDTO(u.getId(),u.getName())).toList();
        return new UserFollowerDTO(user.getId(), user.getName(),followedbyuser);
    }
}
