package com.example.Senla.service;

import com.example.Senla.dto.GradeDTO;

/**
 * @author Ilyas Nigamatullin
 */
public interface GradeService {

  void addGrade(int personId, int grade);

  GradeDTO getGrade(int personId);
}
