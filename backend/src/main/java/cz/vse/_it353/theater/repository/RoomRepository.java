package cz.vse._it353.theater.repository;

import cz.vse._it353.theater.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for Room entity.
 */
public interface RoomRepository extends JpaRepository<Room, String> {
    /**
     * Finds all Room entities by the branch ID.
     * @param branchId the ID of the branch
     * @return a list of Room entities for the specified branch
     */
    List<Room> findAllByBranchId(String branchId);
}