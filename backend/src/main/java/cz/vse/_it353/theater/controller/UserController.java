package cz.vse._it353.theater.controller;

import cz.vse._it353.theater.dto.AddAmountDto;
import cz.vse._it353.theater.dto.AppUserNoPassDto;
import cz.vse._it353.theater.dto.AuthenticationResponse;
import cz.vse._it353.theater.entity.AppUser;
import cz.vse._it353.theater.service.AppUserService;
import cz.vse._it353.theater.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for user-related endpoints.
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin
public class UserController {
    private final AppUserService appUserService;
    private final JwtService jwtService;

    /**
     * Handles a simple greeting endpoint for users.
     * @return a greeting string for the user
     */
    @GetMapping("/")
    public String getUser() {
        return "Hello User!";
    }

    /**
     * Handles getting account details by username.
     * @param username the username of the account
     * @return a response entity containing the account details without the password
     */
    @GetMapping("/account")
    public ResponseEntity<AppUserNoPassDto> getAccountDetails(@RequestParam String username) {
        return ResponseEntity.ok(appUserService.findByUsername(username));
    }

    /**
     * Handles updating account details.
     * @param username the username of the account to be updated
     * @param updatedUser the updated user details
     * @return a response entity containing the new authentication response with a new token
     */
    @PutMapping("/account")
    public ResponseEntity<AuthenticationResponse> updateAccountDetails(@RequestParam String username, @RequestBody AppUser updatedUser) {
        AppUser user = appUserService.updateUserDetails(username, updatedUser);
        String newToken = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthenticationResponse(newToken));
    }

    /**
     * Handles adding balance to a user's account.
     * @param username the username of the account to add balance to
     * @param amount the amount to be added
     * @return a response entity containing the updated account details without the password
     */
    @PostMapping("/addBalance/{username}")
    public ResponseEntity<AppUserNoPassDto> addBalance(@PathVariable String username, @RequestBody AddAmountDto amount) {
        return ResponseEntity.ok(appUserService.addBalance(username, amount));
    }
}