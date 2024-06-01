package cz.vse._it353.theater.service;

import cz.vse._it353.theater.entity.AppUser;
import cz.vse._it353.theater.entity.Projection;
import cz.vse._it353.theater.entity.Reservation;
import cz.vse._it353.theater.repository.ProjectionRepository;
import cz.vse._it353.theater.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final AppUserService appUserService;
    private final ProjectionRepository projectionRepository;

    public Reservation createReservation(String username, String projectionId, boolean paid, Integer discount) {
        if (username == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        if(projectionId == null) {
            throw new IllegalArgumentException("Projection ID must not be null");
        }

        AppUser user = appUserService.findByUsername(username);
        Optional<Projection> projection = projectionRepository.findById(projectionId);

        if (user != null && projection.isPresent()) {
            Reservation reservation = new Reservation();
            reservation.setUser(user);
            reservation.setProjection(projection.get());
            reservation.setPaid(paid);
            reservation.setDiscount(discount);
            return reservationRepository.save(reservation);
        } else {
            throw new IllegalArgumentException("User or projection not found");
        }
    }
    public List<Reservation> findByUsername(String username) {
        return reservationRepository.findAllByUserUsername(username);
    }
}