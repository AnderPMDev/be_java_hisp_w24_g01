package com.socialmeli.SocialMeli.service.implementations;

import com.socialmeli.SocialMeli.dto.responseDTO.FollowerDTO;
import com.socialmeli.SocialMeli.dto.responseDTO.UserFollowedDTO;
import com.socialmeli.SocialMeli.dto.responseDTO.UserFollowerDTO;
import com.socialmeli.SocialMeli.dto.responseDTO.UserFollowersCountDTO;
import com.socialmeli.SocialMeli.entity.User;
import com.socialmeli.SocialMeli.exception.BadRequestException;
import com.socialmeli.SocialMeli.exception.UserNotFollowedException;
import com.socialmeli.SocialMeli.exception.NotFoundException;
import com.socialmeli.SocialMeli.exception.UserNotFoundException;
import com.socialmeli.SocialMeli.repository.interfaces.IUserRepository;
import com.socialmeli.SocialMeli.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    /*
    *   Autowired should not be used here
    *   The repository should be injected in the constructor
    */
    private final IUserRepository userRepository;
    private static final String ASCEND_ORDER = "name_asc";
    private static final String DESCEND_ORDER = "name_desc";

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserFollowersCountDTO getFollowersCount(Integer userId) {
        //Get the ammount of users that follow a certain vendor
        int followersCount = 0;
        //Get the list of followers of the user and count it
        User userFound = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        //Size instead of count, size gives integer as a response.
        followersCount = userFound.getFollowers().size();
        //Return the DTO
        return new UserFollowersCountDTO(userId, userFound.getName(), followersCount);
    }

    @Override
    public Boolean unfollow(Integer userId, Integer userIdToUnfollow) {

        if(userId.equals(userIdToUnfollow)){
            throw new BadRequestException("You can't unfollow yourself");
        }
        //Unfollow a user given its ID
        //If the user is not found, return false
        //If the user is found, remove it from the list of followers and return true
        boolean unfollowed;

        //Search for the user and the user to unfollow
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        User userToUnfollow = userRepository.findById(userIdToUnfollow).orElseThrow(() -> new UserNotFoundException("User not found"));
        //Check if the user to unfollow is in the list of followers of the user
        if (user.getFollowed().contains(userToUnfollow)) {
            user.getFollowed().remove(userToUnfollow);
            //Once the user was removed, delete the user from the list of followers of the user to unfollow
            userToUnfollow.getFollowers().remove(user);
            unfollowed = true; //Return true if everything went well
        }
        else{
            throw new UserNotFollowedException("User not followed");
        }
        return unfollowed;
      //If the user was not found, return false
    }

    @Override
    public UserFollowerDTO follow(Integer idFollower, Integer idFollowed) {

        if(idFollower.equals(idFollowed)){throw new BadRequestException("You can't follow yourself");}

        var user = userRepository.getFollowedUsers(idFollower, idFollowed);

        if (user.isEmpty()) {
            throw new BadRequestException("You already follow this user");
        }else {
            User us =  user.get();
            List<FollowerDTO> followedbyuser = us.getFollowed().stream().map(u -> new FollowerDTO(u.getId(),u.getName())).toList();
            return new UserFollowerDTO(us.getId(), us.getName(),followedbyuser);
        }

    }

    @Override
    public UserFollowerDTO getUserWithFollowers(Integer id, String order) {
        User followers = userRepository.getFollowers(id);

        List<User> followersByOrder;

        if ("name_asc".equals(order) || "name_desc".equals(order)) {
            boolean descendingOrder = "name_desc".equals(order);

            followersByOrder = followers.getFollowers().stream()
                    .sorted(Comparator.comparing(User::getName)
                            .thenComparing(User::getId))
                    .collect(Collectors.toList());

            if (descendingOrder) {
                Collections.reverse(followersByOrder);
            }
        } else {
            throw new BadRequestException("The 'order' parameter is incorrect");
        }

        return new UserFollowerDTO(followers.getId(), followers.getName(), transformFollowers(followersByOrder));
    }

    private List<FollowerDTO> transformFollowers(List<User> followers) {
        List<FollowerDTO> followersDTO = new ArrayList<>();
        followers.forEach(follower -> followersDTO.add(new FollowerDTO(follower.getId(), follower.getName())));
        return followersDTO;
    }
    public boolean userExists(int id) {
        return userRepository.userExists(id);
    }

    @Override
    public UserFollowedDTO getFollowed(int userId, String order) {
        User user = this.getUserByID(userId);
        List<User> followed = new ArrayList<>(user.getFollowed());

        if (order != null && !ASCEND_ORDER.equals(order) && !DESCEND_ORDER.equals(order)) {
            throw new BadRequestException("The 'order' parameter is incorrect");
        }

        List<User> followedByOrder = followed.stream()
                .sorted(order != null ?
                        (ASCEND_ORDER.equals(order) ?
                                Comparator.comparing(User::getName) :
                                Comparator.comparing(User::getName).reversed()) :
                        Comparator.comparing(User::getId))
                .collect(Collectors.toList());

        return new UserFollowedDTO(user.getId(), user.getName(), this.transformFollowed(followedByOrder));
    }

    private List<FollowerDTO> transformFollowed(List<User> f) {
        List<FollowerDTO> UserDTO = new ArrayList<>();
        f.forEach(followed -> UserDTO.add(new FollowerDTO(followed.getId(), followed.getName())));
        return UserDTO;
    }

    private User getUserByID(int userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElseThrow(() -> new UserNotFoundException("User id:  " + userId + " not found"));
    }

}
