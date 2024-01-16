package com.socialmeli.SocialMeli.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.socialmeli.SocialMeli.entity.User;

import java.util.List;

public record UserFollowerDTO(
       Integer id,
       String name,
       List<UserDTO> followed

) {}

