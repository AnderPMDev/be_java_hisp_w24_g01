package com.socialmeli.SocialMeli.dto;

import java.util.List;

public record UserFollowedListDTO(
        int id,
        String name,
        List<UserDTO> followed){
        public int id() {
                return id;
        }
}

