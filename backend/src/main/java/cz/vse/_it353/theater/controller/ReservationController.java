package cz.vse._it353.theater.controller;

import cz.vse._it353.theater.dto.ReservationDto;
import cz.vse._it353.theater.entity.Reservation;
import cz.vse._it353.theater.service.ReservationService;
import jakarta.persistence.OptimisticLockException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for the reservation-related endpoints.
 */
@RestController
@AllArgsConstructor
@CrossOrigin
public class ReservationController {
    private final ReservationService reservationService;

    /**
     * Handles creating a new reservation.
     * @param reservationDto the reservation DTO containing reservation details
     * @return a response entity containing the created reservation or an error message in case of a conflict
     */
    @PostMapping("/reservation")
    public ResponseEntity<?> createReservation(@RequestBody ReservationDto reservationDto) {
        try {
            return ResponseEntity.ok(reservationService.createReservation(reservationDto));
        } catch (OptimisticLockException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("The projection has been updated by another transaction. Please refresh and try again.");
        }
    }

    /**
     * Handles getting reservations by username.
     * @param username the username to filter reservations
     * @return a response entity containing a list of reservations for the specified username
     */
    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getReservations(@RequestParam String username) {
        List<Reservation> reservations = reservationService.findByUsername(username);
        return ResponseEntity.ok(reservations);
    }

    /**
     * Handles updating reservation details.
     * @param id the ID of the reservation to be updated
     * @param reservationDto the reservation DTO containing updated reservation details
     * @return a response entity containing the updated reservation
     */
    @PutMapping("/reservations/{id}")
    public ResponseEntity<Reservation> updateReservationDetails(@PathVariable String id, @RequestBody ReservationDto reservationDto) {
        Reservation updatedReservation = reservationService.updateReservation(id, reservationDto);
        return ResponseEntity.ok(updatedReservation);
    }

    /**
     * Handles deleting a reservation by its ID.
     * @param id the ID of the reservation to be deleted
     * @return a response entity indicating the result of the delete operation
     */
    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<HttpStatus> deleteReservation(@PathVariable String id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}