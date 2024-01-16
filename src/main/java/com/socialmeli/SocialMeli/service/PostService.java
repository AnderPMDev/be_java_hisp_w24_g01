package com.socialmeli.SocialMeli.service;

import com.socialmeli.SocialMeli.dto.PostRequestDTO;
import com.socialmeli.SocialMeli.dto.PostResponseDTO;
import com.socialmeli.SocialMeli.entity.Category;
import com.socialmeli.SocialMeli.entity.Post;
import com.socialmeli.SocialMeli.entity.Product;
import com.socialmeli.SocialMeli.repository.ICategoryRepository;
import com.socialmeli.SocialMeli.repository.IPostRepository;
import com.socialmeli.SocialMeli.repository.IProductRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService implements IPostService{
    private final IPostRepository postRepository;
    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;
    public PostService(IPostRepository postRepository, IProductRepository productRepository, ICategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public PostResponseDTO createPost(PostRequestDTO postDTO) {
        Product product = this.productRepository.findByIdOrCreate(postDTO.product());
        Category category = this.categoryRepository.findByIdOrCreate(postDTO.category());
        int lastId = postRepository.findLastId();
        Post post = postRepository.create(
                new Post(
                        lastId + 1,
                        postDTO.user_id(),
                        postDTO.date(),
                        product,
                        category,
                        postDTO.price())
        );

        return new PostResponseDTO(
                post.getId(),
                post.getUserId(),
                post.getDate().toString(),
                post.getProduct().getId(),
                post.getProduct().getProductName(),
                post.getCategory().getId(),
                post.getCategory().getName(),
                post.getPrice()
        );
    }
}
