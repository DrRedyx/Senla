package com.example.Senla.exception;

/**
 * @author Ilyas Nigamatullin
 */
public class DontOwnerException extends RuntimeException{
  public DontOwnerException(String exception) {
    super(exception);
  }
}
