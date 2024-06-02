package cz.vse._it353.theater.repository;

import cz.vse._it353.theater.entity.Projection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectionRepository extends JpaRepository<Projection, String> {
    Page<Projection> findAll(Pageable pageable);
    List<Projection> findByRoomBranchId(String branchId, Pageable pageable);
    List<Projection> findByRoomBranchId(String branchId);
    List<Projection> findByMovieId(String movieId, Pageable pageable);

}
