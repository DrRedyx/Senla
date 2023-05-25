package com.example.Senla.service.Impl;

import java.util.HashSet;
import java.util.Set;

import com.example.Senla.dto.LoginDTO;
import com.example.Senla.dto.RegisterDTO;
import com.example.Senla.entity.Grade;
import com.example.Senla.entity.Person;
import com.example.Senla.entity.Role;
import com.example.Senla.exception.UserIsAlreadyExists;
import com.example.Senla.exception.UserNotFoundException;
import com.example.Senla.mapper.PersonMapper;
import com.example.Senla.repository.GradeRepo;
import com.example.Senla.repository.PersonRepo;
import com.example.Senla.repository.RoleRepo;
import com.example.Senla.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ilyas Nigamatullin
 */
@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

  @Autowired
  private final PersonRepo personRepo;
  @Autowired
  private final RoleRepo roleRepo;
  @Autowired
  private final GradeRepo gradeRepo;
  @Autowired
  private final PersonMapper personMapper;
  @Autowired
  private final PasswordEncoder passwordEncoder;
  @Autowired
  private final AuthenticationManager authenticationManager;

  @Transactional
  @Override
  public Boolean login(LoginDTO loginDTO) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginDTO.getUsername(),
            loginDTO.getPassword()
        ));
    var person = personRepo.findByUsername(loginDTO.getUsername()).orElseThrow(() -> new UserNotFoundException("not found"));
    return true;
  }

  @Override
  public Boolean register(RegisterDTO registerDTO) {
    if (personRepo.findByUsername(registerDTO.getUsername()).isPresent()) {
      throw new UserIsAlreadyExists("This email is already exists: " + registerDTO.getUsername());
    }
    else {
      Person person = personMapper.toEntity(registerDTO);
      person.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
      Set<Role> roles = new HashSet<>();
      Role role = new Role();
      if (person.getUsername().equals("admin@example.com")) {
        role = roleRepo.findByName("ROLE_ADMIN");
      }
      else {
        role = roleRepo.findByName("ROLE_USER");
      }
      Set<Person> personSet = role.getPersonSet();
      if (personSet == null) {
        personSet = new HashSet<>();
      }
      personSet.add(person);
      role.setPersonSet(personSet);
      roles.add(role);
      person.setRoleSet(roles);
      Grade grade = new Grade();
      grade.setCount(0);
      grade.setGrade(0);
      grade.setAverageGrade(0);
      grade.setPerson(person);
      gradeRepo.save(grade);
      personRepo.save(person);
      roleRepo.save(role);
      return true;
    }
  }
}
