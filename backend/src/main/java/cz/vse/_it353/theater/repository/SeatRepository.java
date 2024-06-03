package cz.vse._it353.theater.repository;

import cz.vse._it353.theater.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Seat entity.
 */
@Repository
public interface SeatRepository extends JpaRepository<Seat, String> {
    /**
     * Finds all Seat entities by the room ID.
     * @param roomId the ID of the room
     * @return a list of Seat entities for the specified room
     */
    List<Seat> findByRoomId(String roomId);

    /**
     * Finds all Seat entities by the room ID, ordered by room row and seat number.
     * @param id the ID of the room
     * @return a list of Seat entities for the specified room, ordered by room row and seat number
     */
    List<Seat> findByRoom_IdOrderByRoomRowAscSeatNumberAsc(String id);

    /**
     * Finds a Seat entity by its ID.
     * @param id the ID of the Seat
     * @return an Optional containing the Seat if found, or empty if not found
     */
    @Override
    Optional<Seat> findById(String id);
}