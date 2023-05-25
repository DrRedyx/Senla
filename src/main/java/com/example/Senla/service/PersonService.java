package com.example.Senla.service;

import java.util.List;

import com.example.Senla.dto.PersonDTO;
import com.example.Senla.dto.UpdatePersonDTO;

/**
 * @author Ilyas Nigamatullin
 */
public interface PersonService {

  List<PersonDTO> getAllPersons();

  PersonDTO updatePerson(String username, UpdatePersonDTO personDTO);

  void deletePerson(int id);

  PersonDTO getMe(String username);

  PersonDTO getById(int id);
}
