package cz.vse._it353.theater.repository;

import cz.vse._it353.theater.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, String> {
    List<Reservation> findAllByUserUsername(String username);
}