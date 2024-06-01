package cz.vse._it353.theater.repository;

import cz.vse._it353.theater.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository  extends JpaRepository<Room, String> {
    //Room findById(String branchId);
}