package com.socialmeli.SocialMeli.exception;

import com.socialmeli.SocialMeli.dto.ExceptionDTO;
import com.socialmeli.SocialMeli.dto.UserNotFoundExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    //Aca los exception handlers @ExceptionHandler
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e){
        ExceptionDTO exceptionDTO = new ExceptionDTO(e.getMessage());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyParameterException.class)
    public ResponseEntity<?> emptyParameter(EmptyParameterException e){
        ExceptionDTO exceptionDto = new ExceptionDTO(e.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> notFound(UserNotFoundException e){
        UserNotFoundExceptionDTO exceptionDto = new UserNotFoundExceptionDTO(e.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFollowedException.class)
    public ResponseEntity<?> notFound(UserNotFollowedException e) {
        UserNotFoundExceptionDTO exceptionDto = new UserNotFoundExceptionDTO(e.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<?> BadRequest(BadRequest e) {
        ExceptionDTO exceptionDto = new ExceptionDTO(e.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);

    }
}
