package com.socialmeli.SocialMeli.controller;

import com.socialmeli.SocialMeli.dto.LastestPostDTO;
import com.socialmeli.SocialMeli.exception.OrderNotFoundException;
import com.socialmeli.SocialMeli.services.ILastestPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.socialmeli.SocialMeli.dto.UserFollowedListDTO;
import com.socialmeli.SocialMeli.service.IUserService;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RestController
public class SocialMeliController {

    @Autowired
    ILastestPostService lastestPostService;
  
    private final IUserService userService;

    public SocialMeliController(IUserService userService) {
        this.userService = userService;
    }

    public String checkOrder(String order){
        Set<String> orders = new HashSet<>(Arrays.asList("date_asc","date_desc"));

        if (order == null ){
            order = "date_desc";
        } else if (!orders.contains(order)) {
            throw new OrderNotFoundException(order);
        }

        return order;
    }

    @GetMapping("/products/followed/{userId}/list")
    public ResponseEntity<LastestPostDTO> listPostUser(@PathVariable Integer userId, @RequestParam(required = false,defaultValue = "date_desc") String order){
        order = checkOrder(order);
        LastestPostDTO post = lastestPostService.getLastestPost(userId,order);
        return ResponseEntity.ok().body(post);
    }



    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<UserFollowedListDTO> getFollowed(@PathVariable int userId,
                                                           @RequestParam(value = "order", required = false) String order){
        return ResponseEntity.ok().body(this.userService.getFollowed(userId, order));
    }

}
