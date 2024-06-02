package cz.vse._it353.theater.controller;

import cz.vse._it353.theater.entity.AppUser;
import cz.vse._it353.theater.service.AppUserService;
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
    @GetMapping("/")
    public String getUser() {
        return "Hello User!";
    }
    @GetMapping("/account")
    public ResponseEntity<AppUser> getAccountDetails(@RequestParam String username) {
        return ResponseEntity.ok(appUserService.findByUsername(username));
    }

    @PutMapping("/account")
    public ResponseEntity<AppUser> updateAccountDetails(@RequestBody AppUser updatedUser) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return ResponseEntity.ok(appUserService.updateUserDetails(username, updatedUser));
    }
}
