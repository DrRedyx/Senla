package com.example.Senla.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

/**
 * @author Ilyas Nigamatullin
 */

@Data
public class AddGradeDTO {

  @Min(value = 1)
  @Max(value = 5)
  private int grade;

}
