package com.example.Senla.Controller;

import com.example.Senla.DTO.PersonDTO;
import com.example.Senla.DTO.UpdatePersonDTO;
import com.example.Senla.Service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Work with people")
public class PersonController {

  @Autowired
  private final PersonService personService;

  @PatchMapping("/update")
  @Operation(summary = "Update my profile")
  public ResponseEntity<PersonDTO> update(@RequestBody UpdatePersonDTO personDTO) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return ResponseEntity.ok(personService.updatePerson(authentication.getName(), personDTO));
  }

  @GetMapping("{id}/get")
  @Operation(summary = "Get 1 person")
  public ResponseEntity<PersonDTO> getPerson(@PathVariable int id) {
    return ResponseEntity.ok(personService.getById(id));
  }

  @GetMapping("/me")
  @Operation(summary = "Get my profile")
  public ResponseEntity<PersonDTO> getMe() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return ResponseEntity.ok(personService.getMe(authentication.getName()));
  }

}
