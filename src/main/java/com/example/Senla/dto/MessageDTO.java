package com.example.Senla.dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * @author Ilyas Nigamatullin
 */

@Data
public class MessageDTO {

  private String authorUsername;

  private String message;

  private LocalDateTime createAt;
}
