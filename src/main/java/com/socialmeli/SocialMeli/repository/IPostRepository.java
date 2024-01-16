package com.socialmeli.SocialMeli.repository;

import com.socialmeli.SocialMeli.entity.Post;

public interface IPostRepository extends ICrudRepository<Post>{

    int findLastId();
}
