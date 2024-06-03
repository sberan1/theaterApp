package cz.vse._it353.theater.service;

import cz.vse._it353.theater.dto.AddAmountDto;
import cz.vse._it353.theater.dto.AppUserNoPassDto;
import cz.vse._it353.theater.entity.AppUser;
import cz.vse._it353.theater.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AppUser findById(String userId) {
        return appUserRepository.findById(userId).orElse(null);
    }

    public AppUserNoPassDto findByUsername(String username) {
        AppUser user = appUserRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));
        AppUserNoPassDto userNoPass = new AppUserNoPassDto(user.getUsername(), user.getEmail(), user.getPhoneNumber(), user.getBalance());
        return userNoPass;
    }
    public AppUser updateUserDetails(String username, AppUser updatedUser) {
        AppUser uzivatel = appUserRepository.findByUsername(username)
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
        return uzivatel;
    }
    public String generateNewToken(AppUser user) {
        return jwtService.generateToken(user);
    }

    public AppUserNoPassDto addBalance(String username, AddAmountDto amount) {
        AppUser user = appUserRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setBalance(user.getBalance().add(BigDecimal.valueOf(amount.getAmount())));
        appUserRepository.save(user);
        return new AppUserNoPassDto(user.getUsername(), user.getEmail(), user.getPhoneNumber(), user.getBalance());
    }
}
