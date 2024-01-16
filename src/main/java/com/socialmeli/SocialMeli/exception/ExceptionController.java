package com.socialmeli.SocialMeli.exception;

import com.socialmeli.SocialMeli.dto.ExceptionDTO.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    //Aca los exception handlers @ExceptionHandler

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<?> BadRequest(BadRequest e) {
        ExceptionDTO exceptionDto = new ExceptionDTO(e.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }
}
