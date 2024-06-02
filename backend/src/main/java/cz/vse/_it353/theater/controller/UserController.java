package cz.vse._it353.theater.controller;

import cz.vse._it353.theater.dto.AppUserNoPassDto;
import cz.vse._it353.theater.dto.AuthenticationResponse;
import cz.vse._it353.theater.entity.AppUser;
import cz.vse._it353.theater.service.AppUserService;
import cz.vse._it353.theater.service.AuthService;
import cz.vse._it353.theater.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin
public class UserController {
    private final AppUserService appUserService;
    private final JwtService jwtService;
    @GetMapping("/")
    public String getUser() {
        return "Hello User!";
    }
    @GetMapping("/account")
    public ResponseEntity<AppUserNoPassDto> getAccountDetails(@RequestParam String username) {
        return ResponseEntity.ok(appUserService.findByUsername(username));
    }

    @PutMapping("/account")
    public ResponseEntity<AuthenticationResponse> updateAccountDetails(@RequestParam String username, @RequestBody AppUser updatedUser) {
        AppUser user = appUserService.updateUserDetails(username, updatedUser);
        String newToken = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthenticationResponse(newToken));
    }
}
