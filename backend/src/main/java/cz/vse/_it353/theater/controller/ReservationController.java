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

@RestController
@AllArgsConstructor
@CrossOrigin
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/reservation")
    public ResponseEntity<?> createReservation(@RequestBody ReservationDto reservationDto) {
        try {
            return ResponseEntity.ok(reservationService.createReservation(reservationDto));
        } catch (OptimisticLockException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("The projection has been updated by another transaction. Please refresh and try again.");
        }
    }
    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getReservations(@RequestParam String username) {
        List<Reservation> reservations = reservationService.findByUsername(username);
        return ResponseEntity.ok(reservations);
    }
    @PutMapping("/reservations/{id}")
    public ResponseEntity<Reservation> updateReservationDetails(@PathVariable String id, @RequestBody ReservationDto reservationDto) {
        Reservation updatedReservation = reservationService.updateReservation(id, reservationDto);
        return ResponseEntity.ok(updatedReservation);
    }
    @DeleteMapping("reservations/{id}")
    public ResponseEntity<HttpStatus> deleteReservation(@PathVariable String id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}