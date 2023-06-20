package com.example.Senla.Service.Impl;

import java.util.HashSet;
import java.util.Set;

import com.example.Senla.DTO.LoginDTO;
import com.example.Senla.DTO.RegisterDTO;
import com.example.Senla.Entity.Grade;
import com.example.Senla.Entity.Person;
import com.example.Senla.Entity.Role;
import com.example.Senla.Exception.UserIsAlreadyExists;
import com.example.Senla.Exception.UserNotFoundException;
import com.example.Senla.Mapper.PersonMapper;
import com.example.Senla.Repository.GradeRepo;
import com.example.Senla.Repository.PersonRepo;
import com.example.Senla.Repository.RoleRepo;
import com.example.Senla.Service.SecurityService;
import lombok.AllArgsConstructor;
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

  @Transactional(readOnly = true)
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

  @Transactional
  @Override
  public Boolean register(RegisterDTO registerDTO) {
    if (personRepo.findByUsername(registerDTO.getUsername()).isPresent()) {
      throw new UserIsAlreadyExists("This email is already exists: " + registerDTO.getUsername());
    }
    else {
      Person person = personMapper.toEntity(registerDTO);
      person.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
      Set<Role> roles = new HashSet<>();
      Role role = roleRepo.findByName("ROLE_USER");
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
