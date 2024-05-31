package cz.vse._it353.theater.controller;

import cz.vse._it353.theater.entity.Movie;
import cz.vse._it353.theater.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin
public class MovieController {
    private final MovieService movieService;
    @GetMapping("/movies")
    public ResponseEntity<String> getMovies() {
        return ResponseEntity.ok("Movies");
    }

    @PostMapping("/movie")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.create(movie));
    }
}
