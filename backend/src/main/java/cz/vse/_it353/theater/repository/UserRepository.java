package cz.vse._it353.theater.repository;

import cz.vse._it353.theater.entity.AppUser;
import cz.vse._it353.theater.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, String> {
    Optional<AppUser> findByUsername(String username);
    AppUser findByEmail(String email);
    AppUser findByPhoneNumber(Integer phoneNumber);
}