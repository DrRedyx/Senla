package com.example.Senla.exception;

/**
 * @author Ilyas Nigamatullin
 */
public class AccessDenied extends RuntimeException{
  public AccessDenied(String message) {
    super(message);
  }
}
