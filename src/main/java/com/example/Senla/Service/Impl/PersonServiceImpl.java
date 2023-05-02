package com.example.Senla.Service.Impl;

import java.util.List;

import com.example.Senla.DTO.PersonDTO;
import com.example.Senla.DTO.UpdatePersonDTO;
import com.example.Senla.Entity.Person;
import com.example.Senla.Exception.UserNotFoundException;
import com.example.Senla.Mapper.PersonMapper;
import com.example.Senla.Repository.PersonRepo;
import com.example.Senla.Service.PersonService;
import lombok.AllArgsConstructor;
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
public class PersonServiceImpl implements PersonService {

  Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);
  @Autowired
  private final PersonRepo personRepo;
  @Autowired
  private final PersonMapper personMapper;

  @Override
  public List<PersonDTO> getAllPersons() {
    logger.info("Get all persons");
    List<Person> personList = personRepo.findAllBy();
    return personMapper.toDtoList(personList);
  }

  @Override
  public PersonDTO updatePerson(String username, UpdatePersonDTO personDTO) {
    Person person = personRepo.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found"));
    logger.info("update person");
    person.setFirstName(personDTO.getFirstName());
    person.setLastName(personDTO.getLastName());
    personRepo.save(person);
    return personMapper.toDTO(person);
  }

  @Override
  public void deletePerson(int id) {
    logger.info("delete person");
    personRepo.deleteById(id);
  }

  @Override
  public PersonDTO getMe(String username) {
    logger.info("Get me");
    return personMapper.toDTO(personRepo.findByEmail(username).get());
  }

  @Override
  public PersonDTO getById(int id) {
    logger.info("get person by id");
    return personMapper.toDTO(personRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found")));
  }
}
