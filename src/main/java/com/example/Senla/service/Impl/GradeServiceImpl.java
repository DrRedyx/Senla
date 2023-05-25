package com.example.Senla.service.Impl;

import com.example.Senla.dto.GradeDTO;
import com.example.Senla.entity.Grade;
import com.example.Senla.entity.Person;
import com.example.Senla.repository.GradeRepo;
import com.example.Senla.repository.PersonRepo;
import com.example.Senla.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ilyas Nigamatullin
 */
@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

  Logger logger = LoggerFactory.getLogger(GradeServiceImpl.class);
  @Autowired
  private final GradeRepo gradeRepo;
  @Autowired
  private final PersonRepo personRepo;

  @Override
  public GradeDTO getGrade(int personId) {
    logger.info("Get grade");
    GradeDTO gradeDTO = new GradeDTO();
    if (gradeRepo.findByPersonId(personId).isPresent()) {
      Grade grade = gradeRepo.findByPersonId(personId).get();
      gradeDTO.setAverageGrade(grade.getAverageGrade());
      return gradeDTO;
    }
    else {
      gradeDTO.setAverageGrade(0);
      return gradeDTO;
    }
  }

  @Override
  public void addGrade(int personId, int grade) {
    Person person = personRepo.findById(personId).orElseThrow(RuntimeException::new);
    Grade raitng = person.getGrade();
    if (raitng == null) {
      logger.info("Set new grade");
      raitng = new Grade();
      raitng.setGrade(grade);
      raitng.setCount(1);
      raitng.setAverageGrade(raitng.getGrade() / raitng.getCount());
      raitng.setPerson(person);
      gradeRepo.save(raitng);
    }
    else {
      logger.info("Upgrade grade");
      raitng.setGrade(raitng.getGrade() + grade);
      raitng.setCount(raitng.getCount() + 1);
      raitng.setAverageGrade(raitng.getGrade() / raitng.getCount());
      gradeRepo.save(raitng);
    }
  }
}
