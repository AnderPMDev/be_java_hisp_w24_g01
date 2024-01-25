package com.socialmeli.SocialMeli.unit.service;

import com.socialmeli.SocialMeli.dto.responseDTO.UserFollowersCountDTO;
import com.socialmeli.SocialMeli.entity.User;
import com.socialmeli.SocialMeli.exception.UserNotFoundException;
import com.socialmeli.SocialMeli.repository.interfaces.IUserRepository;
import com.socialmeli.SocialMeli.service.implementations.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
public class UserServiceTest {
    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("T-0007 Verify that the number of followers of a certain user is correct")
    public void getFollowersCountTest(){
        // Arrange
        int userId = 101;
        String userName = "Alice Johnson";
        ArrayList<User> followers = new ArrayList<>();
        followers.add(new User(102, "Bob Smith", null,null,null));
        followers.add(new User(103, "Charlie Brown", null,null,null));
        User user = new User(userId, userName, followers, null, null);

        UserFollowersCountDTO expected = new UserFollowersCountDTO(userId, userName, followers.size());

        // Act
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        var result = userService.getFollowersCount(userId);

        // Assert
        Assertions.assertEquals(expected, result);
    }
    @Test
    @DisplayName("T-0007 Verify that UserNotFoundException is thrown when user is not found")
    public void getFollowersCountTestUserNotFoundException() {
        // Arrange
        int userId = 10132;

        // Act
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Assert
        Assertions.assertThrows(
                        UserNotFoundException.class,
                        () -> userService.getFollowersCount(userId)
        );
    }

}
