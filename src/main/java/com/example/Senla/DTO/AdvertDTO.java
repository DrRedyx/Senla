package com.example.Senla.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author Ilyas Nigamatullin
 */
@Data
public class AdvertDTO {

  private String title;

  @Size(max = 300)
  private String description;

  private String category;

  private int price;
}
