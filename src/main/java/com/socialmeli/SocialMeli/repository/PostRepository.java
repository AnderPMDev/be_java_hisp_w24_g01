package com.socialmeli.SocialMeli.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.socialmeli.SocialMeli.entity.Post;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PostRepository implements IPostRepository{


    private List<Post> listPosts = new ArrayList<>();


    public PostRepository() throws IOException {
        loadDataBase();
    }

    @Override
    public Object create(Object o) {
        return null;
    }

    @Override
    public Boolean remove(Object o) {
        return null;
    }

    @Override
    public Optional update(Object o) {
        return Optional.empty();
    }

    @Override
    public Optional findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List getAll() {
        return null;
    }

    public List<Post> getAllPostsById(Integer userId) {
        var latestPost = this.listPosts.stream()
                .filter(e-> e.getUserId().equals(userId)
                        && ((e.getDate()).isAfter(LocalDate.now().minusWeeks(2))
                        || (e.getDate()).isEqual(LocalDate.now()))
                ).collect(Collectors.toList());

        return (List<Post>) latestPost;
    }

    private void loadDataBase() throws IOException {
        File file;
        ObjectMapper objectMapper = new ObjectMapper();
        // Registrar el módulo JavaTimeModule para manejar LocalDate
        objectMapper.registerModule(new JavaTimeModule());
        // Registrar el módulo ParameterNamesModule para manejar constructores con nombres de parámetros
        objectMapper.registerModule(new ParameterNamesModule());
        List<Post> posts ;
        file= ResourceUtils.getFile("classpath:json/posts.json");
        posts= objectMapper.readValue(file,new TypeReference<List<Post>>(){});
        listPosts= posts;
    }
}
