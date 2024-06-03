package cz.vse._it353.theater.repository;

import cz.vse._it353.theater.entity.Projection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for Projection entity.
 */
public interface ProjectionRepository extends JpaRepository<Projection, String> {
    /**
     * Finds all Projection entities with pagination support.
     * @param pageable the pagination information
     * @return a paginated list of Projection entities
     */
    Page<Projection> findAll(Pageable pageable);

    /**
     * Finds all Projection entities by the branch ID of the room with pagination support.
     * @param branchId the ID of the branch
     * @param pageable the pagination information
     * @return a list of Projection entities for the specified branch
     */
    List<Projection> findByRoomBranchId(String branchId, Pageable pageable);

    /**
     * Finds all Projection entities by the branch ID of the room.
     * @param branchId the ID of the branch
     * @return a list of Projection entities for the specified branch
     */
    List<Projection> findByRoomBranchId(String branchId);

    /**
     * Finds all Projection entities by the movie ID with pagination support.
     * @param movieId the ID of the movie
     * @param pageable the pagination information
     * @return a list of Projection entities for the specified movie
     */
    List<Projection> findByMovieId(String movieId, Pageable pageable);
    List<Projection> findByMovie_IdAndStartTimeGreaterThan(String id, LocalDateTime startTime, Pageable pageable);
    Page<Projection> findByStartTimeGreaterThan(LocalDateTime time, Pageable pageable);
    List<Projection> findByRoomBranchIdAndStartTimeGreaterThan(String branchId, LocalDateTime time, Pageable pageable);
}
