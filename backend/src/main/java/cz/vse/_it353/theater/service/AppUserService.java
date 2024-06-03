package cz.vse._it353.theater.service;

import cz.vse._it353.theater.dto.AddAmountDto;
import cz.vse._it353.theater.dto.AppUserNoPassDto;
import cz.vse._it353.theater.entity.AppUser;
import cz.vse._it353.theater.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Service class for managing AppUser entities.
 */
@Service
@AllArgsConstructor
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    /**
     * Finds an AppUser by their ID.
     * @param userId the ID of the AppUser
     * @return the AppUser if found, or null if not found
     */
    public AppUser findById(String userId) {
        return appUserRepository.findById(userId).orElse(null);
    }

    /**
     * Finds an AppUser by their username and returns a DTO without the password.
     * @param username the username of the AppUser
     * @return an AppUserNoPassDto containing user details without the password
     */
    public AppUserNoPassDto findByUsername(String username) {
        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return new AppUserNoPassDto(user.getUsername(), user.getEmail(), user.getPhoneNumber(), user.getBalance());
    }

    /**
     * Updates the details of an existing AppUser.
     * @param username the username of the AppUser to update
     * @param updatedUser the updated user details
     * @return the updated AppUser
     */
    public AppUser updateUserDetails(String username, AppUser updatedUser) {
        return appUserRepository.findByUsername(username)
                .map(user -> {
                    user.setUsername(updatedUser.getUsername());
                    user.setEmail(updatedUser.getEmail());
                    user.setPhoneNumber(updatedUser.getPhoneNumber());
                    if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                    }
                    return appUserRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * Generates a new JWT token for the specified AppUser.
     * @param user the AppUser for whom to generate a new token
     * @return the generated JWT token
     */
    public String generateNewToken(AppUser user) {
        return jwtService.generateToken(user);
    }

    /**
     * Adds a specified amount to the balance of an AppUser.
     * @param username the username of the AppUser
     * @param amount the amount to add to the user's balance
     * @return an AppUserNoPassDto containing the updated user details without the password
     */
    public AppUserNoPassDto addBalance(String username, AddAmountDto amount) {
        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setBalance(user.getBalance().add(BigDecimal.valueOf(amount.getAmount())));
        appUserRepository.save(user);
        return new AppUserNoPassDto(user.getUsername(), user.getEmail(), user.getPhoneNumber(), user.getBalance());
    }
}