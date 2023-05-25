package com.example.Senla.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Ilyas Nigamatullin
 */
@Getter
@AllArgsConstructor
public class ExceptionStatus {
  private final String message;
  private final LocalDateTime localDateTime;
  private final HttpStatus httpStatus;
}
