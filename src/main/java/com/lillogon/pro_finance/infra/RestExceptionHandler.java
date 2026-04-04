package com.lillogon.pro_finance.infra;

import com.lillogon.pro_finance.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RestErrorMessage> handleJsonError(HttpMessageNotReadableException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new RestErrorMessage(HttpStatus.BAD_REQUEST, "Invalid request."));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<RestErrorMessage> handleBadRequest(IllegalArgumentException exception){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<RestErrorMessage> handleResourceNotFound(ResourceNotFoundException exception){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestErrorMessage> handleGenericError(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new RestErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error occurred."));
    }
}