package com.example.Senla.dto;

import jakarta.validation.constraints.Max;
import lombok.Data;

/**
 * @author Ilyas Nigamatullin
 */
@Data
public class AdvertDTO {

  public String title;

  @Max(value = 300)
  public String description;

  public String category;

  public int price;
}
