package com.hollowedkeys.publisher.handlers;

import com.hollowedkeys.publisher.exceptions.ErrorResponse;
import com.hollowedkeys.publisher.exceptions.PublisherNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PublisherNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePublisherNotFoundException(PublisherNotFoundException e) {
        return ResponseEntity.status(404).body(new ErrorResponse(e.getMessage(), 404));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return ResponseEntity.status(500).body(new ErrorResponse(e.getMessage(), 500));
    }

}
