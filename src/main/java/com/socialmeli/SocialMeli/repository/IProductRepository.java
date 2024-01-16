package com.socialmeli.SocialMeli.repository;

import com.socialmeli.SocialMeli.dto.ProductPostRequestDTO;
import com.socialmeli.SocialMeli.entity.Product;

public interface IProductRepository extends ICrudRepository<Product>{
    Product findByIdOrCreate(ProductPostRequestDTO product);
}
