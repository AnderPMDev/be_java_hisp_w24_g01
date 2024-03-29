package com.socialmeli.SocialMeli.repository.interfaces;

import com.socialmeli.SocialMeli.dto.requestDTO.CategoryPostRequestDTO;
import com.socialmeli.SocialMeli.entity.Category;

public interface ICategoryRepository extends ICrudRepository<Category>{
    Category findByIdOrCreate(CategoryPostRequestDTO product);
}
