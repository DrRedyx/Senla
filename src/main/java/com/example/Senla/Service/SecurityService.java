package com.example.Senla.Service;

import com.example.Senla.DTO.LoginDTO;
import com.example.Senla.DTO.RegisterDTO;
import com.example.Senla.Entity.Person;

/**
 * @author Ilyas Nigamatullin
 */
public interface SecurityService {

  Boolean login(LoginDTO loginDTO);

  void register(RegisterDTO registerDTO);
}
