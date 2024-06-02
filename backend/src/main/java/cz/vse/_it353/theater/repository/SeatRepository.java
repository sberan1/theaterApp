package cz.vse._it353.theater.repository;

import cz.vse._it353.theater.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, String>{
    List<Seat> findByRoomId(String roomId);
}
