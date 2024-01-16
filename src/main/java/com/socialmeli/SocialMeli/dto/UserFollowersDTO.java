package com.socialmeli.SocialMeli.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record UserFollowersDTO(@JsonProperty("user_id") Integer id, @JsonProperty("user_name") String name, List<FollowerDTO> followers) {
    public UserFollowersDTO(@JsonProperty("user_id") Integer id, @JsonProperty("user_name") String name, List<FollowerDTO> followers) {
        this.id = id;
        this.name = name;
        this.followers = followers;
    }
}
