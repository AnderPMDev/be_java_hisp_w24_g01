package com.socialmeli.SocialMeli.repository;

import com.socialmeli.SocialMeli.dto.CategoryPostRequestDTO;
import com.socialmeli.SocialMeli.dto.ProductPostRequestDTO;
import com.socialmeli.SocialMeli.entity.Category;

public interface ICategoryRepository extends ICrudRepository<Category>{
    Category findByIdOrCreate(CategoryPostRequestDTO product);
}
