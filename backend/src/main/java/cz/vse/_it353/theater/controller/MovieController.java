package cz.vse._it353.theater.controller;

import cz.vse._it353.theater.dto.CreateMovieDto;
import cz.vse._it353.theater.entity.Movie;
import cz.vse._it353.theater.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for the movie-related endpoints.
 */
@RestController
@AllArgsConstructor
@CrossOrigin
public class MovieController {
    private final MovieService movieService;

    /**
     * Handles getting all movies.
     * @param page the page number for pagination, default is 0
     * @param limit the number of movies per page, default is 20
     * @return a response entity containing a list of movies
     */
    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getMovies(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer limit
    ) {
        List<Movie> movies = movieService.findAll(page, limit);
        return ResponseEntity.ok(movies);
    }

    /**
     * Handles adding a new movie.
     * @param movie the movie DTO containing movie details
     * @return a response entity containing the new movie
     */
    @PostMapping("/admin/movie")
    public ResponseEntity<Movie> addMovie(@RequestBody CreateMovieDto movie) {
        return ResponseEntity.ok(movieService.create(movie));
    }

    /**
     * Handles deleting a movie by its ID.
     * @param id the ID of the movie to be deleted
     * @return a response entity containing the deleted movie
     */
    @DeleteMapping("/admin/movie/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable String id) {
        return ResponseEntity.ok(movieService.deleteById(id));
    }

    /**
     * Handles getting a movie by its ID.
     * @param id the ID of the movie
     * @return a response entity containing the movie
     */
    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable String id) {
        return ResponseEntity.ok(movieService.findById(id));
    }

    /**
     * Handles updating a movie.
     * @param id the ID of the movie to be updated
     * @param movie the movie DTO containing updated movie details
     * @return a response entity containing the updated movie
     */
    @PutMapping("/admin/movie/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable String id, @RequestBody CreateMovieDto movie) {
        return ResponseEntity.ok(movieService.updateMovie(id, movie));
    }
}