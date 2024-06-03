package cz.vse._it353.theater.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin
public class HealthCheckController {

    @GetMapping("/health-check")
    public ResponseEntity<HttpStatus> healthCheck() {
        return ResponseEntity.ok(HttpStatus.OK);
    }
}