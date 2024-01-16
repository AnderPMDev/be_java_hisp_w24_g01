package com.socialmeli.SocialMeli.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonBackReference
    private List<User> followed = new ArrayList<>();

}
