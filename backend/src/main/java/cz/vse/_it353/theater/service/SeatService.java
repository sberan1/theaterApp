package cz.vse._it353.theater.service;

import cz.vse._it353.theater.entity.Seat;
import cz.vse._it353.theater.repository.RoomRepository;
import cz.vse._it353.theater.repository.SeatRepository;
import lombok.AllArgsConstructor;
import org.antlr.v4.runtime.misc.IntegerList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;
    private final RoomRepository roomRepository;

    public List<Seat> addRow(String row, Integer numberOfSeats, String roomId) {
        for (int i = 1; i <= numberOfSeats; i++) {
            Seat seat = new Seat();
            seat.setRoomRow(row);
            seat.setSeatNumber(i);
            seat.setRoom(roomRepository.findById(roomId).orElseThrow());
            seatRepository.save(seat);
        }
        return seatRepository.findByRoomId(roomId);
    }

    public List<Seat> findByRoomId(String roomId) {
        return seatRepository.findByRoomId(roomId);
    }
}
