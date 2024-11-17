package com.keyseven.developer.handlers;

import com.keyseven.developer.exceptions.DeveloperNotFoundException;
import com.keyseven.developer.exceptions.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DeveloperNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDeveloperNotFoundException(DeveloperNotFoundException exception) {
        return ResponseEntity
                .status(404)
                .body(new ErrorResponse("Developer Not Found", exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        return ResponseEntity
                .status(500)
                .body(new ErrorResponse("Internal Server Error", exception.getMessage()));
    }

}
