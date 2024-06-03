package cz.vse._it353.theater.service;

import cz.vse._it353.theater.dto.ReservationDto;
import cz.vse._it353.theater.entity.AppUser;
import cz.vse._it353.theater.entity.Projection;
import cz.vse._it353.theater.entity.Reservation;
import cz.vse._it353.theater.entity.Seat;
import cz.vse._it353.theater.repository.AppUserRepository;
import cz.vse._it353.theater.repository.ProjectionRepository;
import cz.vse._it353.theater.repository.ReservationRepository;
import cz.vse._it353.theater.repository.SeatRepository;
import jakarta.persistence.OptimisticLockException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for managing Reservation entities.
 */
@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ProjectionRepository projectionRepository;
    private final AppUserRepository appUserRepository;
    private final SeatRepository seatRepository;

    /**
     * Creates a reservation.
     * @param reservationDto new reservation DTO
     * @return newly created reservation
     */
    @Transactional
    public Reservation createReservation(ReservationDto reservationDto) {
        AppUser user = appUserRepository.findByUsername(reservationDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Projection projection = projectionRepository.findById(reservationDto.getProjectionId())
                .orElseThrow(() -> new IllegalArgumentException("Projection not found"));

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setProjection(projection);
        for(String seatId : reservationDto.getSeats()) {
            reservation.addSeat(seatRepository.findById(seatId).orElseThrow());
        }
        reservation.setPaid(reservationDto.isPaid());
        reservation.setDiscount(reservationDto.getDiscount());

        try {
            return reservationRepository.save(reservation);
        } catch (OptimisticLockException e) {
            throw new IllegalStateException("The projection has been updated by another transaction. Please try again.", e);
        }
    }

    /**
     * Finds a list of reservations by username.
     * @param username username
     * @return a list of reservations
     */
    public List<Reservation> findByUsername(String username) {
        return reservationRepository.findAllByUserUsername(username);
    }

    /**
     * Updates an existing reservation.
     * @param id reservation ID
     * @param reservationDto new reservation DTO
     * @return newly updated reservation
     */
    @Transactional
    public Reservation updateReservation(String id, ReservationDto reservationDto) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        reservation.setPaid(reservationDto.isPaid());
        reservation.setDiscount(reservationDto.getDiscount());
        List<Seat> seats = seatRepository.findAllById(reservationDto.getSeats());
        reservation.setSeats(seats);
        return reservationRepository.save(reservation);
    }

    /**
     * Deletes a reservation.
     * @param id reservation ID
     */
    public void deleteReservation(String id) {
        if (!reservationRepository.existsById(id)) {
            throw new IllegalArgumentException("Reservation not found");
        }
        reservationRepository.deleteById(id);
    }
}