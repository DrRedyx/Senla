package com.example.Senla.Exception;

import java.time.LocalDateTime;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Ilyas Nigamatullin
 */
@Getter
public class ExceptionStatus {
  private final String message;
  private final LocalDateTime localDateTime;

  public ExceptionStatus(String message, LocalDateTime localDateTime) {
    this.message = message;
    this.localDateTime = localDateTime;
  }
}
