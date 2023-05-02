package com.example.Senla.Controller;

import java.util.List;

import com.example.Senla.DTO.PersonDTO;
import com.example.Senla.Service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ilyas Nigamatullin
 */

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

  @Autowired
  private final PersonService personService;

  @GetMapping("/all_person")
  public ResponseEntity<List<PersonDTO>> getAllPersons() {
    return ResponseEntity.ok(personService.getAllPersons());
  }

  @DeleteMapping("{id}/delete")
  public ResponseEntity<?> deletePerson(@PathVariable int id) {
    personService.deletePerson(id);
    return ResponseEntity.ok("Delete person");
  }
}
