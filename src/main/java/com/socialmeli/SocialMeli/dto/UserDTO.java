package com.socialmeli.SocialMeli.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record UserDTO(
        int id,
        String name
) {
        public int id() {
                return id;
        }
}
