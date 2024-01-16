package com.socialmeli.SocialMeli.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialmeli.SocialMeli.entity.User;
import com.socialmeli.SocialMeli.exception.BadRequest;
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
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getFollowedUsers(Integer userId, Integer idToFollow) {

        //find the users
        User follower = listUsers.stream().filter(user -> user.getId().equals(userId)).findFirst().orElse(null);
        User userToFollow = listUsers.stream().filter(user -> user.getId().equals(idToFollow)).findFirst().orElse(null);

        if(follower==null || userToFollow==null){throw new BadRequest("No existen algun(os) usuario");}

        //validate that the user is not already following
        boolean alreadyFollowing = follower.getFollowed().stream().anyMatch(u -> u.getId().equals(idToFollow));

        if(!alreadyFollowing){
            follower.getFollowed().add(userToFollow);
            userToFollow.getFollowers().add(follower);
            return follower;
        }else{
            throw new BadRequest("Ya seguis a este usuario");
        }

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
