package com.example.Senla.exception;

/**
 * @author Ilyas Nigamatullin
 */
public class UserIsAlreadyExists extends RuntimeException {
  public UserIsAlreadyExists(String exception) {
    super(exception);
  }
}
