package com.socialmeli.SocialMeli.unit.service;

import com.socialmeli.SocialMeli.dto.responseDTO.FollowerDTO;
import com.socialmeli.SocialMeli.dto.responseDTO.UserFollowerDTO;
import com.socialmeli.SocialMeli.entity.User;
import com.socialmeli.SocialMeli.exception.BadRequestException;
import com.socialmeli.SocialMeli.repository.implementations.UserRepository;
import com.socialmeli.SocialMeli.service.implementations.UserService;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
public class UserServiceTest {


    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("T-001 Follow user service test")
    public void followUserGoodTest() {
        // Arrange
        Integer userId = 101;
        Integer userIdToFollow = 102;

        //construct the user to return
        UserFollowerDTO userFollowerDTO = new UserFollowerDTO(userId, "Alice Johnson", List.of(new FollowerDTO(userIdToFollow, "Bob Smith")));

        //construct the user received from the repository
        List<User> usersFollowed = List.of(new User(102, "Bob Smith", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        User user = new User(101, "Alice Johnson", new ArrayList<>(), usersFollowed, new ArrayList<>());

        // Act
        Mockito.when(userRepository.getFollowedUsers(userId, userIdToFollow)).thenReturn(Optional.of(user));
        var expected = userService.follow(userId, userIdToFollow);

        // Assert
        Assertions.assertEquals(expected, userFollowerDTO);

    }


    @Test
    @DisplayName("T-001 Follow self user test")
    public void followUserBadTest() {
        // Arrange
        Integer userId = 101;
        Integer userIdToFollow = 101;

        // Act Assert
        Assertions.assertThrows(
                BadRequestException.class,
                () -> userService.follow(userId, userIdToFollow), "The user was not followed correctly");

    }

    @Test
    @DisplayName("T-001 Follow user  already follow  test")
    public void followUserBadTest2() {
        // Arrange
        Integer userId = 101;
        Integer userIdToFollow = 102;


        Mockito.when(userRepository.getFollowedUsers(userId, userIdToFollow)).thenReturn(Optional.empty());
        // Act Assert
        Assertions.assertThrows(
                BadRequestException.class,
                () -> userService.follow(userId, userIdToFollow), "The user was not followed correctly");
    }
}
