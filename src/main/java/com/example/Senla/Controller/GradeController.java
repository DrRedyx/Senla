package com.example.Senla.Controller;

import com.example.Senla.Service.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ilyas Nigamatullin
 */

@RestController
@RequestMapping("/grade")
@AllArgsConstructor
public class GradeController {

  @Autowired
  private final GradeService gradeService;


  @PostMapping("{person_id}/add/{grade}")
  public ResponseEntity<?> addGradeToPerson(@PathVariable("person_id") int personId, @PathVariable int grade) {
    gradeService.addGrade(personId, grade);
    return ResponseEntity.ok().build();
  }
}
