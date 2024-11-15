package com.keyseven.genre.handlers;

import com.keyseven.genre.exceptions.ErrorResponse;
import com.keyseven.genre.exceptions.GenreAlreadyExistsException;
import com.keyseven.genre.exceptions.GenreNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GenreNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleGenreNotFoundException(GenreNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse("Genre Not Found", e.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(GenreAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleGenreAlreadyExistsException(GenreAlreadyExistsException e) {
        ErrorResponse errorResponse = new ErrorResponse("Genre Already Exists", e.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse("Internal Server Error", e.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
