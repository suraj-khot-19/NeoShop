package com.suraj.NeoShop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    /// product not found exception
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDetails> accountNotFound(WebRequest request, ProductNotFoundException exception) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                "PRODUCT_NOT_FOUND",
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
