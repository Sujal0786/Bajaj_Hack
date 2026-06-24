package com.example.bfhl.exception;

import com.example.bfhl.dto.BfhlResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<BfhlResponse> handleBadRequest(Exception ex) {
        return ResponseEntity.ok(BfhlResponse.failure());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BfhlResponse> handleGeneralException(Exception ex) {
        return ResponseEntity.ok(BfhlResponse.failure());
    }
}
