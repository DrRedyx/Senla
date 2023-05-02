package com.example.Senla.Service;

import com.example.Senla.DTO.GradeDTO;

/**
 * @author Ilyas Nigamatullin
 */
public interface GradeService {

  void addGrade(int personId, int grade);

  GradeDTO getGrade(int personId);
}
