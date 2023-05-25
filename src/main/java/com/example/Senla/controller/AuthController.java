package com.example.Senla.controller;

import com.example.Senla.dto.LoginDTO;
import com.example.Senla.dto.RegisterDTO;
import com.example.Senla.security.JWTUtil;
import com.example.Senla.service.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ilyas Nigamatullin
 */
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

  @Autowired
  private final SecurityService securityService;
  @Autowired
  private final JWTUtil jwtUtil;

  @PostMapping("/register")
  public ResponseEntity<String> registration(@RequestBody RegisterDTO registerDTO) {
    if (securityService.register(registerDTO)) {
      String token = jwtUtil.generateAccessToken(registerDTO.getUsername());
      return ResponseEntity.ok(token);
    }
    else {
      return ResponseEntity.ok("invalid token");
    }
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
    if (securityService.login(loginDTO)) {
      String token = jwtUtil.generateAccessToken(loginDTO.getUsername());
      return ResponseEntity.ok(token);
    }
    else {
      return ResponseEntity.ok("This user already bean registered");
    }
  }
}
