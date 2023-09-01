package com.betrybe.agrix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ControllerAdvice.
 */
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
  @ExceptionHandler(NotFoundError.class)
  public ResponseEntity<String> handleNotFound(NotFoundError error) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
  }

}

