package com.example.CardProductService.Handler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import feign.FeignException;

@RestControllerAdvice
public class GlobalValidationHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().stream().findFirst().ifPresent(error ->
            errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleIllegalArgs(IllegalArgumentException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", ex.getMessage());
    return error;
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleIllegalArgs(NoSuchElementException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", "Deposit Product Not Found");
    return error;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleInvalidEnum(HttpMessageNotReadableException ex) {
            return ResponseEntity.badRequest().body("Invalid enum value provided: " + ex.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleIllegalStatus(IllegalStateException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", ex.getMessage());
    return error;
    }

    @ExceptionHandler(FeignException.NotFound.class)
    public Map<String, String> handleFeignNotFound(FeignException.NotFound ex) {
        Map<String, String> error = new HashMap<>();
        String errorMessage = ex.getMessage();
        if (errorMessage != null && errorMessage.contains("error")) {
            int startIndex = errorMessage.indexOf("error\":\"") + 8; // Dynamically find the start index
            int endIndex = errorMessage.indexOf("\"", startIndex);  // Dynamically find the end index
            String parsedMessage = errorMessage.substring(startIndex, endIndex);
            error.put("error", parsedMessage);
        }
        return error;
    }
}
