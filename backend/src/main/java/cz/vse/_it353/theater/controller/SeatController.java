package cz.vse._it353.theater.controller;

import cz.vse._it353.theater.entity.Seat;
import cz.vse._it353.theater.service.SeatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class SeatController {
    private final SeatService seatService;

    @PostMapping("/admin/addRow")
    public ResponseEntity<List<Seat>> addSeats(
            @RequestParam String row,
            @RequestParam Integer numberOfSeats,
            @RequestParam String roomId
    ) {
        return ResponseEntity.ok(seatService.addRow(row, numberOfSeats, roomId));
    }

    @GetMapping("/seats")
    public ResponseEntity<List<Seat>> getSeats(@RequestParam String roomId) {
        return ResponseEntity.ok(seatService.findByRoomId(roomId));
    }
}
