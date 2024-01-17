package com.socialmeli.SocialMeli.controller;

import com.socialmeli.SocialMeli.dto.UserFollowedListDTO;
import com.socialmeli.SocialMeli.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class SocialMeliController {
    private final IUserService userService;

    public SocialMeliController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<UserFollowedListDTO> getFollowed(@PathVariable int userId,
                                                           @RequestParam(value = "order", required = false) String order){
        return ResponseEntity.ok().body(this.userService.getFollowed(userId, order));
    }

}
