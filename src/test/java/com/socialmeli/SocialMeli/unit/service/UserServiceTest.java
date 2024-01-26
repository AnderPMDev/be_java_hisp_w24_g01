package com.socialmeli.SocialMeli.unit.service;

import com.socialmeli.SocialMeli.entity.User;
import com.socialmeli.SocialMeli.exception.UserNotFoundException;
import com.socialmeli.SocialMeli.repository.interfaces.IUserRepository;
import com.socialmeli.SocialMeli.service.implementations.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("When unfollowing an existing user, then unfollow successfully")
    public void unfollowExistingUser() {
        // Arrange
        // Create user and userToUnfollow objects to simulate existing users in the system.
        User user = new User(101, "Alice Johnson", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        User userToUnfollow = new User(102, "Bob Smith", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        // Add userToUnfollow to user's followed list and user to userToUnfollow's followers list to simulate an existing following relationship.
        user.getFollowed().add(userToUnfollow);
        userToUnfollow.getFollowers().add(user);

        // Mock the userRepository's response to return these users when their IDs are searched.
        Mockito.when(userRepository.findById(101)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findById(102)).thenReturn(Optional.of(userToUnfollow));

        // Act
        // Call the unfollow method and store the result (true/false).
        boolean result = userService.unfollow(101, 102);

        // Assert
        // Check if the unfollow operation returned true indicating success.
        Assertions.assertTrue(result, "Unfollow operation should return true");
        // Ensure userToUnfollow is removed from user's followed list.
        Assertions.assertFalse(user.getFollowed().contains(userToUnfollow), "User should be removed from followed list");
        // Ensure user is removed from userToUnfollow's followers list.
        Assertions.assertFalse(userToUnfollow.getFollowers().contains(user), "User should be removed from followers list of userToUnfollow");
    }

    @Test
    @DisplayName("When unfollowing a non-existing user, then handle gracefully")
    public void unfollowNonExistingUser() {
        // Arrange
        int userId = 101; // Existing user ID
        int nonExistentUserId = 999; // Non-existing user ID

        // Create a user object to simulate an existing user in the system.
        User user = new User(101, "Alice Johnson", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act & Assert
        // Assert that a UserNotFoundException is thrown when trying to unfollow a non-existing user.
        Assertions.assertThrows(
                UserNotFoundException.class,
                () -> userService.unfollow(userId, nonExistentUserId),
                "Unfollowing a non-existing user should throw UserNotFoundException"
        );
    }

}