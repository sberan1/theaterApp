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
}