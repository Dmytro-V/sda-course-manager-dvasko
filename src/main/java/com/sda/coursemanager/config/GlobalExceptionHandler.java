package com.sda.coursemanager.config;

import com.sda.coursemanager.exceptions.NotFoundException;
import com.sda.coursemanager.exceptions.WrongUserTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String handException(NotFoundException exception) {
        return "got an 404 error with: " + exception.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(WrongUserTypeException.class)
    public String handException(WrongUserTypeException exception) {
        return "got an 404 error with: " + exception.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handException(MethodArgumentNotValidException exception) {
        return "wrong object";
    }

}
