package com.example.Senla.DTO;

import jakarta.validation.constraints.Max;
import lombok.Data;

/**
 * @author Ilyas Nigamatullin
 */

@Data
public class AddCommentDTO {
  @Max(value = 300)
  private String comment;
}
