package com.socialmeli.SocialMeli.services;

import com.socialmeli.SocialMeli.dto.FollowerDTO;
import com.socialmeli.SocialMeli.dto.UserDTO;
import com.socialmeli.SocialMeli.entity.User;
import com.socialmeli.SocialMeli.exception.EmptyParameterException;
import com.socialmeli.SocialMeli.exception.NotFoundException;
import com.socialmeli.SocialMeli.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getUserWithFollowers(Integer id, String order) {
        User followers = userRepository.getFollowers(id);
        if (followers == null)
            throw new NotFoundException("No se encontró el usuario");

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
            throw new NotFoundException("El parámetro 'order' es incorrecto");
        }

        return new UserDTO(followers.getId(), followers.getName(), transformFollowers(followersByOrder));
    }

    private List<FollowerDTO> transformFollowers(List<User> followers) {
        List<FollowerDTO> followersDTO = new ArrayList<>();
        followers.forEach(follower -> followersDTO.add(new FollowerDTO(follower.getId(), follower.getName())));
        return followersDTO;
    }

}
