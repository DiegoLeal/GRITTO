package gritto.teste.security.controller;

import gritto.teste.security.dto.LoginRequest;
import gritto.teste.security.dto.LoginResponse;
import gritto.teste.security.dto.RegisterRequest;
import gritto.teste.security.dto.RegisterResponse;
import gritto.teste.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    return ResponseEntity.ok(authService.login(loginRequest));
  }

  @PostMapping("/register")
  public ResponseEntity<RegisterResponse> registerUsuario(@Valid @RequestBody RegisterRequest novaConta) {
    var usuario = authService.register(novaConta);

    return new ResponseEntity<>(
            new RegisterResponse(usuario),
            HttpStatus.CREATED
    );
  }

}
