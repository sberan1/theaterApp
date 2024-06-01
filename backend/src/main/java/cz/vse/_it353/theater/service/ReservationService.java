package cz.vse._it353.theater.service;

import cz.vse._it353.theater.entity.AppUser;
import cz.vse._it353.theater.entity.Projection;
import cz.vse._it353.theater.entity.Reservation;
import cz.vse._it353.theater.repository.AppUserRepository;
import cz.vse._it353.theater.repository.ProjectionRepository;
import cz.vse._it353.theater.repository.ReservationRepository;
import jakarta.persistence.OptimisticLockException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ProjectionRepository projectionRepository;
    private final AppUserRepository appUserRepository;

    @Transactional
    public Reservation createReservation(String username, String projectionId, boolean isPaid, int discount) {
        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Projection projection = projectionRepository.findById(projectionId)
                .orElseThrow(() -> new IllegalArgumentException("Projection not found"));

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setProjection(projection);
        reservation.setPaid(isPaid);
        reservation.setDiscount(discount);

        try {
            return reservationRepository.save(reservation);
        } catch (OptimisticLockException e) {
            throw new IllegalStateException("The projection has been updated by another transaction. Please try again.", e);
        }
    }
    public List<Reservation> findByUsername(String username) {
        return reservationRepository.findAllByUserUsername(username);
    }
}