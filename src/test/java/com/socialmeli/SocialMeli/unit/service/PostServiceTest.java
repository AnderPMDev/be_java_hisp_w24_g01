package com.socialmeli.SocialMeli.unit.service;

import com.socialmeli.SocialMeli.dto.requestDTO.CategoryPostRequestDTO;
import com.socialmeli.SocialMeli.dto.requestDTO.ProductPostRequestDTO;
import com.socialmeli.SocialMeli.dto.responseDTO.ExceptionDTO;
import com.socialmeli.SocialMeli.dto.responseDTO.LastestPostDTO;
import com.socialmeli.SocialMeli.dto.responseDTO.PostWithIdDTO;
import com.socialmeli.SocialMeli.entity.Category;
import com.socialmeli.SocialMeli.entity.Post;
import com.socialmeli.SocialMeli.entity.Product;
import com.socialmeli.SocialMeli.entity.User;
import com.socialmeli.SocialMeli.exception.OrderNotFoundException;
import com.socialmeli.SocialMeli.repository.interfaces.ICategoryRepository;
import com.socialmeli.SocialMeli.repository.interfaces.IPostRepository;
import com.socialmeli.SocialMeli.repository.interfaces.IProductRepository;
import com.socialmeli.SocialMeli.repository.interfaces.IUserRepository;
import com.socialmeli.SocialMeli.service.implementations.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class PostServiceTest {

    @Mock
    private IPostRepository postRepository;
    @Mock
    private IProductRepository productRepository;
    @Mock
    private ICategoryRepository categoryRepository;
    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private PostService postService;

    private final ProductPostRequestDTO productPostRequestDTOId204 = new ProductPostRequestDTO(
            204,
            "Wireless Earbuds",
            "Electronics",
            "Apple",
            "White",
            "Active noise cancellation, sweat-resistant"
    );
    private final ProductPostRequestDTO productPostRequestDTOId205 = new ProductPostRequestDTO(
            205,
            "Backpack",
            "Outdoor",
            "Patagonia",
            "Green",
            "Durable and water-resistant"
    );
    private final CategoryPostRequestDTO categoryPostRequestDTOId3 = new CategoryPostRequestDTO(
            3,
            "Appliances"
    );
    private final PostWithIdDTO postWithIdDTOId304 = new PostWithIdDTO(
            104,
            304,
            LocalDate.of(2024,1,23),
            productPostRequestDTOId204,
            categoryPostRequestDTOId3,
            149.99
    );
    private final PostWithIdDTO postWithIdDTOId305 = new PostWithIdDTO(
            105,
            305,
            LocalDate.of(2024,1,24),
            productPostRequestDTOId205,
            categoryPostRequestDTOId3,
            89.99
    );
    private final Product productId204 = new Product(
            204,
            "Wireless Earbuds",
            "Electronics",
            "Apple",
            "White",
            "Active noise cancellation, sweat-resistant"
    );
    private final Category categoryId3 = new Category(
            3,
            "Appliances"
    );
    private final Post postId304 = new Post(
            304,
            104,
            LocalDate.of(2024,1,23),
            productId204,
            categoryId3,
            149.99
    );

    private final Product productId205 = new Product(
            205,
            "Backpack",
            "Outdoor",
            "Patagonia",
            "Green",
            "Durable and water-resistant"
    );
    private final Post postId305 = new Post(
            305,
            105,
            LocalDate.of(2024,1,24),
            productId205,
            categoryId3,
            89.99
    );
    @Test
    @DisplayName("T-0005 - Order not found")
    void orderNotFoundTest(){
        //Arrange
        String incorrectOrder = "price_asc";
        Integer userId = 101;
        //Act & Assert
        assertThrows(OrderNotFoundException.class, () -> postService.getLastestPost(userId, incorrectOrder));
    }
    @Test
    @DisplayName("T-0006-desc - Get latest posts order by date desc")
    void getLatestPostsOrderByDateDescTest(){
        //Arrange
        String order = "date_desc";
        Integer userId = 101;
        LastestPostDTO expectedDTO = new LastestPostDTO(
                101,
                List.of(postWithIdDTOId305, postWithIdDTOId304)
        );
        List<Post> postListUser104 = List.of(postId304);
        List<Post> postListUser105 = List.of(postId305);
        User user = new User(
                101,
                "Alice Johnson",
                List.of(
                        new User(104, "David Williams"),
                        new User(105, "Eva Martinez")
                )
        );

        //Act
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(postRepository.getAllPostsById(104)).thenReturn(postListUser104);
        when(postRepository.getAllPostsById(105)).thenReturn(postListUser105);
        when(productRepository.findByIdOrCreate(productPostRequestDTOId204)).thenReturn(productId204);
        when(productRepository.findByIdOrCreate(productPostRequestDTOId205)).thenReturn(productId205);
        when(categoryRepository.findByIdOrCreate(categoryPostRequestDTOId3)).thenReturn(categoryId3);
        LastestPostDTO lastestPostDTO = postService.getLastestPost(userId, order);
        //Assert
        assertEquals(expectedDTO, lastestPostDTO, "The latest posts should be ordered by date desc");
    }

    @Test
    @DisplayName("T-0006-asc - Get latest posts order by date asc")
    void getLatestPostsOrderByDateAscTest(){
        //Arrange
        String order = "date_asc";
        Integer userId = 101;
        LastestPostDTO expectedDTO = new LastestPostDTO(
                101,
                List.of(postWithIdDTOId304, postWithIdDTOId305)
        );
        List<Post> postListUser104 = List.of(postId304);
        List<Post> postListUser105 = List.of(postId305);
        User user = new User(
                101,
                "Alice Johnson",
                List.of(
                        new User(104, "David Williams"),
                        new User(105, "Eva Martinez")
                )
        );

        //Act
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(postRepository.getAllPostsById(104)).thenReturn(postListUser104);
        when(postRepository.getAllPostsById(105)).thenReturn(postListUser105);
        when(productRepository.findByIdOrCreate(productPostRequestDTOId204)).thenReturn(productId204);
        when(productRepository.findByIdOrCreate(productPostRequestDTOId205)).thenReturn(productId205);
        when(categoryRepository.findByIdOrCreate(categoryPostRequestDTOId3)).thenReturn(categoryId3);
        LastestPostDTO lastestPostDTO = postService.getLastestPost(userId, order);
        //Assert
        assertEquals(expectedDTO, lastestPostDTO, "The latest posts should be ordered by date asc");
    }

    @Test
    @DisplayName("T-0006 - Get latest posts with no order should use default 'DESC'")
    void getLatestPostsTest(){
        //Arrange
        Integer userId = 101;
        LastestPostDTO expectedDTO = new LastestPostDTO(
                101,
                List.of(postWithIdDTOId305, postWithIdDTOId304)
        );
        List<Post> postListUser104 = List.of(postId304);
        List<Post> postListUser105 = List.of(postId305);
        User user = new User(
                101,
                "Alice Johnson",
                List.of(
                        new User(104, "David Williams"),
                        new User(105, "Eva Martinez")
                )
        );

        //Act
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(postRepository.getAllPostsById(104)).thenReturn(postListUser104);
        when(postRepository.getAllPostsById(105)).thenReturn(postListUser105);
        when(productRepository.findByIdOrCreate(productPostRequestDTOId204)).thenReturn(productId204);
        when(productRepository.findByIdOrCreate(productPostRequestDTOId205)).thenReturn(productId205);
        when(categoryRepository.findByIdOrCreate(categoryPostRequestDTOId3)).thenReturn(categoryId3);
        LastestPostDTO lastestPostDTO = postService.getLastestPost(userId, null);
        //Assert
        assertEquals(expectedDTO, lastestPostDTO, "The latest posts should be ordered by default (DESC)");
    }
}
