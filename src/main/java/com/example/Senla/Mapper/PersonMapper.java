package com.example.Senla.Mapper;

import java.util.List;

import com.example.Senla.DTO.PersonDTO;
import com.example.Senla.DTO.RegisterDTO;
import com.example.Senla.DTO.UpdatePersonDTO;
import com.example.Senla.Entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

/**
 * @author Ilyas Nigamatullin
 */
@Mapper(componentModel = "spring")
@Component
public interface PersonMapper {

  Person toEntity(RegisterDTO registerDTO);

  @Mapping(source = "grade.averageGrade", target = "grade")
  PersonDTO toDTO(Person person);

  Person updateToEntity(UpdatePersonDTO updatePersonDTO);

  List<PersonDTO> toDtoList(List<Person> personList);

}
