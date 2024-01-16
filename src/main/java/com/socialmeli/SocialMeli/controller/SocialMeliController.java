package com.socialmeli.SocialMeli.controller;

import com.socialmeli.SocialMeli.dto.ExceptionDTO.ExceptionDTO;
import com.socialmeli.SocialMeli.dto.UserFollowerDTO;
import com.socialmeli.SocialMeli.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocialMeliController {


    UserService userService;
    public SocialMeliController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<UserFollowerDTO> followUser(@PathVariable Integer  userId, @PathVariable Integer userIdToFollow) {
       return new ResponseEntity<>(userService.follow(userId, userIdToFollow), HttpStatus.OK);

    }
}
