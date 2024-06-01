package cz.vse._it353.theater.repository;

import cz.vse._it353.theater.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, String> {
}
