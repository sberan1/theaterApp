package cz.vse._it353.theater.repository;

import cz.vse._it353.theater.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository  extends JpaRepository<Room, String> {
    List<Room> findAllByBranchId(String branchId);
}
