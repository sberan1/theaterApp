package cz.vse._it353.theater.controller;

import cz.vse._it353.theater.entity.Seat;
import cz.vse._it353.theater.service.SeatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for the seat-related endpoints.
 */
@RestController
@CrossOrigin
@AllArgsConstructor
public class SeatController {
    private final SeatService seatService;

    /**
     * Handles adding a row of seats to a room.
     * @param row the row identifier
     * @param numberOfSeats the number of seats in the row
     * @param roomId the ID of the room to add the row of seats
     * @return a response entity containing the list of added seats
     */
    @PostMapping("/admin/addRow")
    public ResponseEntity<List<Seat>> addSeats(
            @RequestParam String row,
            @RequestParam Integer numberOfSeats,
            @RequestParam String roomId
    ) {
        return ResponseEntity.ok(seatService.addRow(row, numberOfSeats, roomId));
    }

    /**
     * Handles getting seats by room ID.
     * @param roomId the ID of the room
     * @return a response entity containing the list of seats in the specified room
     */
    @GetMapping("/seats")
    public ResponseEntity<List<Seat>> getSeats(@RequestParam String roomId) {
        return ResponseEntity.ok(seatService.findByRoomId(roomId));
    }
}