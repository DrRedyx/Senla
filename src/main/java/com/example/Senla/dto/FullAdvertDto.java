package com.example.Senla.dto;

import lombok.Data;

/**
 * @author Ilyas Nigamatullin
 */
@Data
public class FullAdvertDto {

  private String ownerFirstName;

  private String ownerLastName;

  private String title;

  private String description;

  private Integer price;
}
