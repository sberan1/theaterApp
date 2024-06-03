package cz.vse._it353.theater.repository;

import cz.vse._it353.theater.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for AppUser entity.
 */
public interface AppUserRepository extends JpaRepository<AppUser, String> {
    /**
     * Finds an AppUser by their username.
     * @param username the username of the AppUser
     * @return an Optional containing the AppUser if found, or empty if not found
     */
    Optional<AppUser> findByUsername(String username);
}