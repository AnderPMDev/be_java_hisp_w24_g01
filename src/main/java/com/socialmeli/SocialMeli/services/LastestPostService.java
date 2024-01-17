package com.socialmeli.SocialMeli.services;

import com.socialmeli.SocialMeli.dto.LastestPostDTO;
import com.socialmeli.SocialMeli.dto.PostWithIdDTO;
import com.socialmeli.SocialMeli.exception.EmptyListException;
import com.socialmeli.SocialMeli.exception.UserNotFoundException;
import com.socialmeli.SocialMeli.repository.CategoryRepository;
import com.socialmeli.SocialMeli.repository.PostRepository;
import com.socialmeli.SocialMeli.repository.ProductRepository;
import com.socialmeli.SocialMeli.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class LastestPostService implements ILastestPostService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public LastestPostDTO getLastestPost(Integer userId, String order) {

        LastestPostDTO lastestPostDTO = new LastestPostDTO();
        //Se trae al usuario y lo mandamos al repositorio
        var user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("El usuario " + userId + " no existe"));
        var usersFollowed = user.getFollowed();
        if (usersFollowed.isEmpty()) {//Si no sigue a nadie, lanzamos una excepción
            throw new EmptyListException("No te encuentras siguiendo a ningún usuario");
        }
        lastestPostDTO.setUserId(user.getId());

        //Se llaman a los posts por usuario
        List<PostWithIdDTO> postFiltered = new ArrayList<>();
        usersFollowed.forEach((uf) ->{
            postRepository.getAllPostsById(uf.getId()).stream().forEach(post -> {
                System.out.println(post);
                var searchCategory = categoryRepository.findByIdOrCreate(post.getCategory());
                var searchProduct = productRepository.findByIdOrCreate(post.getProduct());
                PostWithIdDTO postWithIdDTO = new PostWithIdDTO(post.getUserId(), post.getId(), post.getDate(), searchProduct, searchCategory, post.getPrice());
                postFiltered.add(postWithIdDTO);
            });
        });
        lastestPostDTO.setPost(postFiltered);
        if (lastestPostDTO.getPost().isEmpty()) {//Si no hay contenido, lanzamos una excepción
            throw new EmptyListException("No se encuentran publicaciones de las últimas dos semanas");
        }

        switch (order) {
            case "date_asc":
                lastestPostDTO.getPost().sort(Comparator.comparing(dto -> dto.getDate()));
                break;
            case "date_desc":
                lastestPostDTO.getPost().sort(Comparator.comparing(dto -> ((PostWithIdDTO) dto).getDate()).reversed());
                break;
        }

        return lastestPostDTO;
    }
}
