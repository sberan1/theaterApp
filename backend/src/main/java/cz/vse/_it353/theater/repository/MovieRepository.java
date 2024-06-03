package cz.vse._it353.theater.repository;

import cz.vse._it353.theater.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Movie entity.
 */
public interface MovieRepository extends JpaRepository<Movie, String> {
    /**
     * Finds all Movie entities with pagination support.
     * @param pageable the pagination information
     * @return a paginated list of Movie entities
     */
    Page<Movie> findAll(Pageable pageable);
}