package com.example.Senla.dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
    *@author Ilyas Nigamatullin
*/

@Data
public class CommentDTO {

  private String comment;

  private LocalDateTime createAt;
}
