package com.socialmeli.SocialMeli.service;

import com.socialmeli.SocialMeli.dto.UserDTO;
import com.socialmeli.SocialMeli.dto.UserFollowedListDTO;
import com.socialmeli.SocialMeli.entity.User;
import com.socialmeli.SocialMeli.exception.NotFoundException;
import com.socialmeli.SocialMeli.exception.UserNotFoundException;
import com.socialmeli.SocialMeli.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<User> followed = new ArrayList<>(user.getFollowed());

        if (order != null && !ASCEND_ORDER.equals(order) && !DESCEND_ORDER.equals(order)) {
            throw new NotFoundException("El parámetro 'order' es incorrecto");
        }

        List<User> followedByOrder = followed.stream()
                .sorted(order != null ?
                        (ASCEND_ORDER.equals(order) ?
                                Comparator.comparing(User::getName) :
                                Comparator.comparing(User::getName).reversed()) :
                        Comparator.comparing(User::getId))
                .collect(Collectors.toList());

        return new UserFollowedListDTO(user.getId(), user.getName(), this.transformFollowed(followedByOrder));
    }

    private List<UserDTO> transformFollowed(List<User> f) {
        List<UserDTO> UserDTO = new ArrayList<>();
        f.forEach(followed -> UserDTO.add(new UserDTO(followed.getId(), followed.getName())));
        return UserDTO;
    }
    private User getUserByID(int userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElseThrow(() -> new UserNotFoundException("No se encontró el usuario con id " + userId));
    }

}
