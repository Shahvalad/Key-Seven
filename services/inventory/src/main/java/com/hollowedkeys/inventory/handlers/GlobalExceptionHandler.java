package com.hollowedkeys.inventory.handlers;

import com.hollowedkeys.inventory.exceptions.GameKeyIsAlreadyRedeemedException;
import com.hollowedkeys.inventory.exceptions.GameKeyNotFoundException;
import com.hollowedkeys.inventory.exceptions.GameNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GameKeyNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleGameKeyNotFoundException(GameKeyNotFoundException ex) {
        return ResponseEntity.status(404).body(new ErrorResponse(ex.getMessage(), "NOT_FOUND"));
    }
    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleGameNotFoundException(GameNotFoundException ex) {
        return ResponseEntity.status(404).body(new ErrorResponse(ex.getMessage(), "NOT_FOUND"));
    }
    @ExceptionHandler(GameKeyIsAlreadyRedeemedException.class)
    public ResponseEntity<ErrorResponse> handleGameKeyIsAlreadyRedeemedException(GameKeyIsAlreadyRedeemedException ex) {
        return ResponseEntity.status(400).body(new ErrorResponse(ex.getMessage(), "BAD_REQUEST"));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return ResponseEntity.status(500).body(new ErrorResponse(ex.getMessage(), "INTERNAL_SERVER_ERROR"));
    }

}
