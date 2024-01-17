package com.socialmeli.SocialMeli.dto;


public record UserDTO(
        Integer id,
        String name
) {
        public Integer id() {
                return id;
        }
}
