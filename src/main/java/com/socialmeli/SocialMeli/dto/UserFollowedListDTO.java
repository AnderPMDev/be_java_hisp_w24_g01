package com.socialmeli.SocialMeli.dto;

import com.socialmeli.SocialMeli.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFollowedListDTO{
        Integer id;
        String name;
        List<User> followed;
}
