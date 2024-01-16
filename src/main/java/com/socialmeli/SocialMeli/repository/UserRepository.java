package com.socialmeli.SocialMeli.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialmeli.SocialMeli.entity.Product;
import com.socialmeli.SocialMeli.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {

    private List<User> listUsers = new ArrayList<>();

    public UserRepository() throws IOException {
        loadDataBase();

    }
    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public Boolean remove(User user) {
        return null;
    }

    @Override
    public Optional<User> update(User user) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(Integer id) {
        //Find a user given its ID
        //If the user is not found, return an empty optional
        return listUsers.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    private void loadDataBase() throws IOException {
        File file;
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users;
        file= ResourceUtils.getFile("classpath:json/users.json");
        users= objectMapper.readValue(file,new TypeReference<List<User>>(){});
        listUsers= users;
    }
}
