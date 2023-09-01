package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.AuthResponseDto;
import com.betrybe.agrix.controllers.dto.AuthenticationDto;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  /**
   * Constructor de AuthController.
   */
  @Autowired
  public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  /**
   * Método login.
   */
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AuthenticationDto authenticationDto) {
    UsernamePasswordAuthenticationToken getUser = new UsernamePasswordAuthenticationToken(
        authenticationDto.username(), authenticationDto.password());
    Authentication auth = authenticationManager.authenticate(getUser);

    Person person = (Person) auth.getPrincipal();

    String token = tokenService.generateToken(person);

    return ResponseEntity.status(HttpStatus.OK).body(new AuthResponseDto(token));
  }
}
