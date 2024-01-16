package com.socialmeli.SocialMeli.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private Integer id;
    private String name;
    private List<User> followers = new ArrayList<>();
    private List<User> followed = new ArrayList<>();
    private List<Post> posts = new ArrayList<>();

}
