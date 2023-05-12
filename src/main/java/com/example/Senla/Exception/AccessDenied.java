package com.example.Senla.Exception;

/**
 * @author Ilyas Nigamatullin
 */
public class AccessDenied extends RuntimeException{
  public AccessDenied(String message) {
    super(message);
  }
}
