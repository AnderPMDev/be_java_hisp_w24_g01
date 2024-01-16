package com.socialmeli.SocialMeli.exception;

import com.socialmeli.SocialMeli.dto.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    //Aca los exception handlers @ExceptionHandler

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> notFound(UserNotFoundException e){
        ExceptionDTO exceptionDTO = new ExceptionDTO(e.getMessage());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }
}
