package cz.vse._it353.theater.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
    @GetMapping("/movies")
    public ResponseEntity<String> getMovies() {
        return ResponseEntity.ok("Movies");
    }

    @PostMapping("admin/movie")
    public ResponseEntity<String> addMovie() {
        return ResponseEntity.ok("Movie added");
    }
}
