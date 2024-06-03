package cz.vse._it353.theater.repository;

import cz.vse._it353.theater.dto.AppUserNoPassDto;
import cz.vse._it353.theater.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    Optional<AppUser> findByUsername(String username);
}