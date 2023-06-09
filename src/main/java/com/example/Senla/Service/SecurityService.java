package com.example.Senla.Service;

import com.example.Senla.DTO.LoginDTO;
import com.example.Senla.DTO.RegisterDTO;
/**
 * @author Ilyas Nigamatullin
 */
public interface SecurityService {

  Boolean login(LoginDTO loginDTO);

  Boolean register(RegisterDTO registerDTO);
}
