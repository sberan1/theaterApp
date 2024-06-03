package cz.vse._it353.theater.repository;

import cz.vse._it353.theater.entity.Projection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ProjectionRepository extends JpaRepository<Projection, String> {
    Page<Projection> findByStartTimeGreaterThan(LocalDateTime time, Pageable pageable);
    List<Projection> findByRoomBranchIdAndStartTimeGreaterThan(String branchId, LocalDateTime time, Pageable pageable);
    List<Projection> findByRoomBranchId(String branchId);
    List<Projection> findByMovieId(String movieId, Pageable pageable);

    List<Projection> findByMovie_IdAndStartTimeGreaterThan(String id, LocalDateTime startTime, Pageable pageable);
}
