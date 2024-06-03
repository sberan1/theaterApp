package cz.vse._it353.theater.repository;

import cz.vse._it353.theater.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for Reservation entity.
 */
public interface ReservationRepository extends JpaRepository<Reservation, String> {
    /**
     * Finds all Reservation entities by the username of the user.
     * @param username the username of the user
     * @return a list of Reservation entities for the specified user
     */
    List<Reservation> findAllByUserUsername(String username);
}