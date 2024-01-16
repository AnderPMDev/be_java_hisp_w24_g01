package com.socialmeli.SocialMeli.service;

import com.socialmeli.SocialMeli.dto.UserDTO;
import com.socialmeli.SocialMeli.dto.UserFollowedListDTO;
import com.socialmeli.SocialMeli.entity.User;
import com.socialmeli.SocialMeli.exception.UserNotFoundException;
import com.socialmeli.SocialMeli.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
@Service
public class UserService implements IUserService{

    private static final String ASCEND_ORDER = "name_asc";
    private static final String DESCEND_ORDER = "name_desc";
    private IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean userExists(int id) {
        return userRepository.userExists(id);
    }

    @Override
    public UserFollowedListDTO getFollowed(int userId, String order) {
        User user = this.getUserByID(userId);
        List<User> followed = user.getFollowed();

        if (order != null) {
            if (order.equals(ASCEND_ORDER)) {
                followed.sort(Comparator.comparing(User::getName));
            } else if (order.equals(DESCEND_ORDER)) {
                followed.sort(Comparator.comparing(User::getName).reversed());
            }
        }

        return new UserFollowedListDTO(user.getId(), user.getName(), followed);
    }
    private User getUserByID(int userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElseThrow(() -> new UserNotFoundException("No se encontró el usuario con id " + userId));
    }

}
