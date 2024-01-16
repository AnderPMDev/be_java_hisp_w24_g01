package com.socialmeli.SocialMeli.controller;

import com.socialmeli.SocialMeli.dto.UserDTO;
import com.socialmeli.SocialMeli.dto.UserFollowerDTO;
import com.socialmeli.SocialMeli.dto.UserFollowersDTO;
import com.socialmeli.SocialMeli.service.IUserService;
import com.socialmeli.SocialMeli.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {


    IUserService userService;
    public UserController(UserService userService){
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

    @GetMapping("/users/{userId}/followers/list")
    public ResponseEntity<UserFollowersDTO> getFollowersOfUser(@PathVariable Integer userId,
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
