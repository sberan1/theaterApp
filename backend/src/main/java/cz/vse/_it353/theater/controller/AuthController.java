package cz.vse._it353.theater.controller;

import cz.vse._it353.theater.dto.AuthenticationResponse;
import cz.vse._it353.theater.dto.LoginUserDto;
import cz.vse._it353.theater.dto.RegisterUserDto;
import cz.vse._it353.theater.entity.AppUser;
import cz.vse._it353.theater.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LoginUserDto user
    ) {
        return ResponseEntity.ok(authService.login(user));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> signup(
            @RequestBody RegisterUserDto user
            ) {
        return ResponseEntity.ok(authService.signup(user));
    }
}
