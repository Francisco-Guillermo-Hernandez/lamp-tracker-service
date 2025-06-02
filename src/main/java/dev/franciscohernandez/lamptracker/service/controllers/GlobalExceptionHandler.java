package dev.franciscohernandez.lamptracker.service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.franciscohernandez.lamptracker.service.models.ServiceResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<ServiceResponse> handleException(Exception ex) {
    //     ServiceResponse errorResponse = new ServiceResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    //     return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    // }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ServiceResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        ServiceResponse errorResponse = new ServiceResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
