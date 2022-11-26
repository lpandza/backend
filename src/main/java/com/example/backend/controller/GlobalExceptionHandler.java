package com.example.backend.controller;

import com.example.backend.exception.ErrorMessage;
import com.example.backend.exception.ErrorMessageBadRequest;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(IllegalArgumentException.class)
  public ErrorMessage handleBadRequestException(RuntimeException ex) {
    return new ErrorMessage(ex.getMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public List<ErrorMessageBadRequest> handleBadRequestException(MethodArgumentNotValidException exc) {
    List<ObjectError> errors = exc.getAllErrors();

    return errors.stream()
        .map(error -> (FieldError) error)
        .map(error -> new ErrorMessageBadRequest(error.getField(), error.getDefaultMessage()))
        .collect(Collectors.toList());
  }
}
