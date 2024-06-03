package cz.vse._it353.theater.repository;

import cz.vse._it353.theater.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for AppUser entity.
 */
public interface UserRepository extends JpaRepository<AppUser, String> {
    /**
     * Finds an AppUser by their username.
     * @param username the username of the AppUser
     * @return an Optional containing the AppUser if found, or empty if not found
     */
    Optional<AppUser> findByUsername(String username);

    /**
     * Finds an AppUser by their email.
     * @param email the email of the AppUser
     * @return the AppUser with the specified email
     */
    AppUser findByEmail(String email);

    /**
     * Finds an AppUser by their phone number.
     * @param phoneNumber the phone number of the AppUser
     * @return the AppUser with the specified phone number
     */
    AppUser findByPhoneNumber(Integer phoneNumber);
}