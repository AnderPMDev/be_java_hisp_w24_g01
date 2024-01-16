package com.socialmeli.SocialMeli.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
    }

    public UserNotFoundException(Integer id) {
        super("No se encontro usuario con id: " + id);
    }
}
