package cz.vse._it353.theater.controller;

import cz.vse._it353.theater.dto.ReservationDto;
import cz.vse._it353.theater.entity.Reservation;
import cz.vse._it353.theater.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/reservation")
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationDto reservationDto) {
        Reservation reservation = reservationService.createReservation(
                reservationDto.getUserId(),
                reservationDto.getProjectionId(),
                reservationDto.isPaid(),
                reservationDto.getDiscount()
        );
        return ResponseEntity.ok(reservation);
    }
}