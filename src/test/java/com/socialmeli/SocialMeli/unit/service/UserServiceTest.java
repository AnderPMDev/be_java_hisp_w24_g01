package com.socialmeli.SocialMeli.unit.service;

import com.socialmeli.SocialMeli.dto.responseDTO.FollowerDTO;
import com.socialmeli.SocialMeli.dto.responseDTO.UserFollowerDTO;
import com.socialmeli.SocialMeli.entity.Post;
import com.socialmeli.SocialMeli.entity.User;
import com.socialmeli.SocialMeli.exception.BadRequestException;
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
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;
    @InjectMocks
    private UserService userService;

    private final List<User> FOLLOWERS = new ArrayList<>();
    private final List<User> FOLLOWED = new ArrayList<>();
    private final List<Post> POSTS = new ArrayList<>();
    private final List<FollowerDTO> FOLLOWERSDTO = List.of(
            new FollowerDTO(102, "Bob Smith"),
            new FollowerDTO(103, "Charlie Brown"));
    private final List<FollowerDTO> FOLLOWERSREVERSEDDTO = List.of(
            new FollowerDTO(103, "Charlie Brown"),
            new FollowerDTO(102, "Bob Smith")
            );
    private final UserFollowerDTO USER1DTO = new UserFollowerDTO(
                101,
             "Alice Johnson",
            FOLLOWERSDTO);
    private final UserFollowerDTO USER1FOLLOWERSREVERSEDTO = new UserFollowerDTO(
            101,
            "Alice Johnson",
            FOLLOWERSREVERSEDDTO);

    private final User USER1 = new User(101, "Alice Johnson" ,FOLLOWERS, FOLLOWED, POSTS);
    private boolean isValidateExceptionTest = false;

    private void validateAlphabeticalOrderTest(int userId, String order, boolean isAscendingOrder) {

        //arrange
        UserFollowerDTO expected = isAscendingOrder ? USER1DTO : USER1FOLLOWERSREVERSEDTO;

        FOLLOWERS.add(new User(102, "Bob Smith", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        FOLLOWERS.add(new User(103, "Charlie Brown", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        FOLLOWED.add(new User(104, "David Williams", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        FOLLOWED.add(new User(105, "Eva Martinez", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));

        //act
        Mockito.when(userRepository.getFollowers(userId)).thenReturn(USER1);

        if (isValidateExceptionTest) {
            //assert
            Assertions.assertThrows(
                    BadRequestException.class,
                    () -> userService.getUserWithFollowers(userId, order)
            );
        }
        else {
            //act
            UserFollowerDTO result = userService.getUserWithFollowers(userId, order);

            //assert
            Assertions.assertEquals(expected, result, "The value of the 'order' parameter is incorrect");
        }
    }

    @Test
    @DisplayName("Test that validates the value of the alphabetical order parameter for a value null")
    public void validateAlphabeticalOrderExceptionNullParameterTest(){
        //arrange
        isValidateExceptionTest = true;
        validateAlphabeticalOrderTest(101, null, true);
    }
    @Test
    @DisplayName("Test that validates the value of the alphabetical order parameter for a value empty")
    public void validateAlphabeticalOrderExceptionEmptyParameterTest(){
        //arrange
        isValidateExceptionTest = true;
        validateAlphabeticalOrderTest(101, " ", true);
    }
    @Test
    @DisplayName("Test that validates the value of the alphabetical order parameter for a value other than 'name_asc' or 'name_desc'")
    public void validateAlphabeticalOrderExceptionBadParameterTest(){
        //arrange
        isValidateExceptionTest = true;
        validateAlphabeticalOrderTest(101, "mal", true);
    }
    @Test
    @DisplayName("Test that validates the correct functionality of alphabetical order ascending")
    public void getUserWithFollowersAscTest(){
        //arrange
        validateAlphabeticalOrderTest(101, "name_asc", true);
    }
    @Test
    @DisplayName("Test that validates the correct functionality of alphabetical order descending")
    public void getUserWithFollowersDescTest(){
        //arrange
        validateAlphabeticalOrderTest(101, "name_desc", false);
    }

}
