package cz.vse._it353.theater.service;

import cz.vse._it353.theater.entity.Seat;
import cz.vse._it353.theater.repository.RoomRepository;
import cz.vse._it353.theater.repository.SeatRepository;
import lombok.AllArgsConstructor;
import org.antlr.v4.runtime.misc.IntegerList;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing Seat entities.
 */
@Service
@AllArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;
    private final RoomRepository roomRepository;

    /**
     * Adds new row.
     * @param row name of row
     * @param numberOfSeats number of seats
     * @param roomId room ID
     * @return list of seats
     */
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

    /**
     * Finds seats by room ID.
     * @param roomId room ID
     * @return a list of seats
     */
    public List<Seat> findByRoomId(String roomId) {
        return seatRepository.findByRoom_IdOrderByRoomRowAscSeatNumberAsc(roomId);
    }
}
