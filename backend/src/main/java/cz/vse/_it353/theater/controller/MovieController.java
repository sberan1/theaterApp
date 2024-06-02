package cz.vse._it353.theater.controller;

import cz.vse._it353.theater.dto.CreateMovieDto;
import cz.vse._it353.theater.entity.Movie;
import cz.vse._it353.theater.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
public class MovieController {
    private final MovieService movieService;
    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getMovies() {
        List<Movie> movies = movieService.findAll();
        return ResponseEntity.ok(movies);
    }

    @PostMapping("/admin/movie")
    public ResponseEntity<Movie> addMovie(@RequestBody CreateMovieDto movie) {
        return ResponseEntity.ok(movieService.create(movie));
    }
}
