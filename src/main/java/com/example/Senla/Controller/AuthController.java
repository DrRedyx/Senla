package com.example.Senla.Controller;

import com.example.Senla.DTO.LoginDTO;
import com.example.Senla.DTO.RegisterDTO;
import com.example.Senla.DTO.TokenDTO;
import com.example.Senla.Security.JWTUtil;
import com.example.Senla.Service.SecurityService;
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
  public ResponseEntity<?> registration(@RequestBody RegisterDTO registerDTO) {
    securityService.register(registerDTO);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/login")
  public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO) {
    String token = null;
    TokenDTO tokenDTO = new TokenDTO();
    if (securityService.login(loginDTO)) {
      token = jwtUtil.generateAccessToken(loginDTO.getUsername());
      tokenDTO.setToken(token);
    }
    return ResponseEntity.ok(tokenDTO);
  }
}
