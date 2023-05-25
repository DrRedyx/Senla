package com.example.Senla.service;

import com.example.Senla.dto.LoginDTO;
import com.example.Senla.dto.RegisterDTO;

/**
 * @author Ilyas Nigamatullin
 */
public interface SecurityService {

  Boolean login(LoginDTO loginDTO);

  Boolean register(RegisterDTO registerDTO);
}
