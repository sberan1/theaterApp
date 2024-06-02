package cz.vse._it353.theater.service;

import cz.vse._it353.theater.entity.AppUser;
import cz.vse._it353.theater.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService {
    private final AppUserRepository appUserRepository;

    public AppUser findById(String userId) {
        return appUserRepository.findById(userId).orElse(null);
    }

    public AppUser findByUsername(String username) {
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
    public AppUser updateUserDetails(String username, AppUser updatedUser) {
        return appUserRepository.findByUsername(username)
                .map(user -> {
                    user.setUsername(updatedUser.getUsername());
                    user.setPassword(updatedUser.getPassword());
                    user.setEmail(updatedUser.getEmail());
                    user.setPhoneNumber(updatedUser.getPhoneNumber());
                    return appUserRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}