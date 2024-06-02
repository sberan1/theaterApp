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
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ProjectionRepository projectionRepository;
    private final AppUserRepository appUserRepository;
    private final SeatRepository seatRepository;

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
    public List<Reservation> findByUsername(String username) {
        return reservationRepository.findAllByUserUsername(username);
    }
    @Transactional
    public Reservation updateReservation(String id, ReservationDto reservationDto) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        reservation.setPaid(reservationDto.isPaid());
        reservation.setDiscount(reservationDto.getDiscount());
        List<Seat> seats = seatRepository.findAllById(reservationDto.getSeats());
        reservation.setSeats(seats);
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(String id) {
        if (!reservationRepository.existsById(id)) {
            throw new IllegalArgumentException("Reservation not found");
        }
        reservationRepository.deleteById(id);
    }
}