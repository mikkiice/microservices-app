package com.example.post_service.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(PostRejectException.class)
  public ResponseEntity<String> handlePostReject(PostRejectException ex) {
    return switch (ex.getMessage()) {
      case "noPostsError" -> ResponseEntity.status(HttpStatus.NO_CONTENT).body("User has no posts");
      case "badWordsError", "maxLengthError" -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation failed: " + ex.getReason());
      default -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
    };
  }
}

