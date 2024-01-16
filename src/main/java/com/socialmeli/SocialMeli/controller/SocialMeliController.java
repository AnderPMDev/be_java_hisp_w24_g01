package com.socialmeli.SocialMeli.controller;
import com.socialmeli.SocialMeli.dto.UserDTO;
import com.socialmeli.SocialMeli.exception.EmptyParameterException;
import com.socialmeli.SocialMeli.services.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.socialmeli.SocialMeli.dto.ExceptionDTO.ExceptionDTO;
import com.socialmeli.SocialMeli.dto.UserFollowerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SocialMeliController {
    IUserService userService;

    public SocialMeliController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{userId}/followers/list")
    public ResponseEntity<UserDTO> getFollowersOfUser(@PathVariable Integer userId,
                                                @RequestParam(required = false, defaultValue = "name_asc") String order) {
        if(userId == null)
            throw new IllegalArgumentException("El id del usuario es incorrecto");
        return ResponseEntity.ok(userService.getUserWithFollowers(userId, order));
    }

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<UserFollowerDTO> followUser(@PathVariable Integer  userId, @PathVariable Integer userIdToFollow) {
       return new ResponseEntity<>(userService.follow(userId, userIdToFollow), HttpStatus.OK);

    }
}
