package com.socialmeli.SocialMeli.unit.service;

import com.socialmeli.SocialMeli.dto.requestDTO.CategoryPostRequestDTO;
import com.socialmeli.SocialMeli.dto.requestDTO.ProductPostRequestDTO;
import com.socialmeli.SocialMeli.dto.responseDTO.LastestPostDTO;
import com.socialmeli.SocialMeli.dto.responseDTO.PostWithIdDTO;
import com.socialmeli.SocialMeli.entity.Category;
import com.socialmeli.SocialMeli.entity.Post;
import com.socialmeli.SocialMeli.entity.Product;
import com.socialmeli.SocialMeli.entity.User;
import com.socialmeli.SocialMeli.exception.EmptyListException;
import com.socialmeli.SocialMeli.repository.implementations.CategoryRepository;
import com.socialmeli.SocialMeli.repository.implementations.PostRepository;
import com.socialmeli.SocialMeli.repository.implementations.ProductRepository;
import com.socialmeli.SocialMeli.repository.implementations.UserRepository;
import com.socialmeli.SocialMeli.service.implementations.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    private static LastestPostDTO lastestPostDTO = new LastestPostDTO(
            101,
            List.of(new PostWithIdDTO(
                    104,
                    301,
                    LocalDate.of(2024, 1,20),
                    new ProductPostRequestDTO(
                            201,
                            "Smartphone",
                            "Electronics",
                            "Samsung",
                            "Black",
                            "6.5-inch display, 128GB storage"
                    ),
                    new CategoryPostRequestDTO(
                            1,
                            "Electronics"
                    ),
                    799.99
            ))
    );

    private static Post post1 = new Post(
            301,
            104,
            LocalDate.of(2024, 1,20),
            new Product(
                    201,
                    "Smartphone",
                    "Electronics",
                    "Samsung",
                    "Black",
                    "6.5-inch display, 128GB storage"
            ),
            new Category(
                    1,
                    "Electronics"
            ),
            799.99
    );

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private PostService postService;

    @Test
    @DisplayName("Given a userId and return a list of posts those are from the last two weeks")
    public void testGetLastestPostHappyPath() {
        //Arrange
        Integer userId = 101;
        LastestPostDTO expected = lastestPostDTO;

        //Act
        //when - then return
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of( User.builder().id(userId).followed(List.of(User.builder().id(104).build())).build()));
        Mockito.when(postRepository.getAllPostsById(104)).thenReturn(List.of( post1));
        Mockito.when(categoryRepository.findByIdOrCreate(new CategoryPostRequestDTO(1, "Electronics"))).thenReturn(new Category(1, "Electronics"));
        Mockito.when(productRepository.findByIdOrCreate(new ProductPostRequestDTO(201,
                "Smartphone",
                "Electronics",
                "Samsung",
                "Black",
                "6.5-inch display, 128GB storage"))).thenReturn(new Product(201,
                "Smartphone",
                "Electronics",
                "Samsung",
                "Black",
                "6.5-inch display, 128GB storage"));

        LastestPostDTO result = postService.getLastestPost(userId, "date_desc");

        //Assert
        Assertions.assertEquals(expected, result, "The lists are not the same");
    }

    @Test
    @DisplayName("No posts found from the last two weeks")
    public void testGetLastestPostEmptyListException() {
        //Arrange
        Integer userId = 101;

        //Act
        //when - then return
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of( User.builder().id(userId).followed(List.of(User.builder().id(104).build())).build()));
        Mockito.when(postRepository.getAllPostsById(104)).thenReturn(Collections.emptyList());

        //Assert
        Assertions.assertThrows(
                EmptyListException.class,
                () -> postService.getLastestPost(userId, "date_desc")
        );
    }

}
