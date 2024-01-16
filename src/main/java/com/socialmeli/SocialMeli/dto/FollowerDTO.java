package com.socialmeli.SocialMeli.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FollowerDTO(@JsonProperty("user_id") Integer id,@JsonProperty("user_name") String name) {
}
