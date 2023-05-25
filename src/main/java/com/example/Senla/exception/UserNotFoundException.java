package com.example.Senla.exception;

/**
 * @author Ilyas Nigamatullin
 */
public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String exception) {
    super(exception);
  }
}
