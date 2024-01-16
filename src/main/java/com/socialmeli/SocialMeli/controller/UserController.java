package com.socialmeli.SocialMeli.controller;

import com.socialmeli.SocialMeli.service.IUserService;
import com.socialmeli.SocialMeli.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    IUserService userService;
    public UserController(UserServiceImpl userService){
        this.userService = userService;
    }

    //Obtener el resultado de la cantidad de usuarios que siguen a un determinado vendedor
    ///GET users/{userId}/followers/count
    @GetMapping("/users/{userId}/followers/count")
    public ResponseEntity<?> getFollowersCount(@PathVariable Integer userId){
        return new ResponseEntity<>(userService.getFollowersCount(userId), HttpStatus.OK);
    }

    //Poder realizar la acción de “Unfollow” (dejar de seguir) a un determinado vendedor.
    // POST /users/{userId}/unfollow/{userIdToUnfollow}
    //Bodyless POST
    @PostMapping("/users/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<?> unfollow(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow){
        return new ResponseEntity<>(userService.unfollow(userId, userIdToUnfollow), HttpStatus.OK);
    }
}
