package cz.vse._it353.theater.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

/**
 * Controller for checking the connection to the server
 */
@RestController
@CrossOrigin
public class HealthCheckController {

    /**
     * Checks if the connection client-server is alive
     * @return an http status ensuring the connection
     */
    @GetMapping("/health-check")
    public ResponseEntity<HttpStatus> healthCheck() {
        return ResponseEntity.ok(HttpStatus.OK);
    }
}