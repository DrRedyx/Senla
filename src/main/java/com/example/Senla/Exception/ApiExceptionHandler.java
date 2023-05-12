package com.example.Senla.Exception;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Ilyas Nigamatullin
 */
@ControllerAdvice
public class ApiExceptionHandler {

  Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

  @ExceptionHandler(value = {AdvertNotFoundException.class})
  protected ResponseEntity<Object> handleAdvertNotFound(AdvertNotFoundException e) {
    ExceptionStatus exceptionStatus = setExceptionStatus(e.getMessage());
    return ResponseEntity.ok(exceptionStatus);
  }
  @ExceptionHandler(value = {DontOwnerException.class})
  protected ResponseEntity<Object> handleDontOwner(DontOwnerException e) {
    ExceptionStatus exceptionStatus = setExceptionStatus(e.getMessage());
    return ResponseEntity.ok(exceptionStatus);
  }
  @ExceptionHandler(value = {FindCategoryException.class})
  protected ResponseEntity<Object> handleFindCategory(FindCategoryException e) {
    ExceptionStatus exceptionStatus = setExceptionStatus(e.getMessage());
    return ResponseEntity.ok(exceptionStatus);
  }
  @ExceptionHandler(value = {UserIsAlreadyExists.class})
  protected ResponseEntity<Object> handleUsername(UserIsAlreadyExists e) {
    ExceptionStatus exceptionStatus = setExceptionStatus(e.getMessage());
    return ResponseEntity.ok(exceptionStatus);
  }
  @ExceptionHandler(value = {UserNotFoundException.class})
  protected ResponseEntity<Object> handleUserNotFound(UserNotFoundException e) {
    ExceptionStatus exceptionStatus = setExceptionStatus(e.getMessage());
    return ResponseEntity.ok(exceptionStatus);
  }
  @ExceptionHandler(value = {AccessDenied.class})
  protected ResponseEntity<Object> handleAccess(AccessDenied e) {
    ExceptionStatus exceptionStatus = setExceptionStatus(e.getMessage());
    return ResponseEntity.ok(exceptionStatus);
  }

  protected ExceptionStatus setExceptionStatus(String errorMessage) {
    logger.error(errorMessage);

    return new ExceptionStatus(
        errorMessage,
        LocalDateTime.now()
    );
  }
}
