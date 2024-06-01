package cz.vse._it353.theater.repository;

import cz.vse._it353.theater.entity.Projection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectionRepository extends JpaRepository<Projection, String> {
    List<Projection> findByRoomBranchId(String branchId);
    List<Projection> findByMovieId(String movieId);
    List<Projection> findAll();
}