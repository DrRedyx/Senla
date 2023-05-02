package com.example.Senla.Controller;

import com.example.Senla.DTO.PersonDTO;
import com.example.Senla.DTO.UpdatePersonDTO;
import com.example.Senla.Service.PersonService;
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

  @PatchMapping("/update")
  public ResponseEntity<PersonDTO> update(@RequestBody UpdatePersonDTO personDTO) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return ResponseEntity.ok(personService.updatePerson(authentication.getName(), personDTO));
  }

  @GetMapping("{id}/get")
  public ResponseEntity<PersonDTO> getPerson(@PathVariable int id) {
    return ResponseEntity.ok(personService.getById(id));
  }

  @GetMapping("/me")
  public ResponseEntity<PersonDTO> getMe() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return ResponseEntity.ok(personService.getMe(authentication.getName()));
  }

}
