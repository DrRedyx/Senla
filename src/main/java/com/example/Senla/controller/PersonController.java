package com.example.Senla.controller;

import com.example.Senla.dto.PersonDTO;
import com.example.Senla.dto.UpdatePersonDTO;
import com.example.Senla.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ilyas Nigamatullin
 */

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

  @Autowired
  private final PersonService personService;

  @PatchMapping()
  public ResponseEntity<PersonDTO> update(@RequestBody UpdatePersonDTO personDTO) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return ResponseEntity.ok(personService.updatePerson(authentication.getName(), personDTO));
  }

  @GetMapping("/{id}")
  public ResponseEntity<PersonDTO> getPerson(@PathVariable int id) {
    return ResponseEntity.ok(personService.getById(id));
  }

  @GetMapping("/me")
  public ResponseEntity<PersonDTO> getMe() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return ResponseEntity.ok(personService.getMe(authentication.getName()));
  }

}
