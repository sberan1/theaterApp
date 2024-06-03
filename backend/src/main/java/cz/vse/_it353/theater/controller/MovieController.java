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
    public ResponseEntity<List<Movie>> getMovies(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer limit
    ) {
        List<Movie> movies = movieService.findAll(page, limit);
        return ResponseEntity.ok(movies);
    }

    @PostMapping("/admin/movie")
    public ResponseEntity<Movie> addMovie(@RequestBody CreateMovieDto movie) {
        return ResponseEntity.ok(movieService.create(movie));
    }

    @DeleteMapping("/admin/movie/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable String id) {
        return ResponseEntity.ok(movieService.deleteById(id));
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable String id) {
        return ResponseEntity.ok(movieService.findById(id));
    }

    @PutMapping("/admin/movie/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable String id, @RequestBody CreateMovieDto movie) {
        return ResponseEntity.ok(movieService.updateMovie(id, movie));
    }
}
