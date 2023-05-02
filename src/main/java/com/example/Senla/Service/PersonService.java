package com.example.Senla.Service;

import java.util.List;

import com.example.Senla.DTO.PersonDTO;
import com.example.Senla.DTO.UpdatePersonDTO;

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
