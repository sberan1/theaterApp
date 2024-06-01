package cz.vse._it353.theater.controller;

import cz.vse._it353.theater.dto.ProjectionDto;
import cz.vse._it353.theater.entity.Movie;
import cz.vse._it353.theater.entity.Price;
import cz.vse._it353.theater.entity.Projection;
import cz.vse._it353.theater.entity.Room;
import cz.vse._it353.theater.service.ProjectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
public class ProjectionController {
    private final ProjectionService projectionService;

    @PostMapping("/projection")
    public ResponseEntity<Projection> addProjection(@RequestBody ProjectionDto projectionDto) {
        Projection projection = new Projection();
        projection.setStartTime(projectionDto.getStartTime());
        projection.setMovie(new Movie(projectionDto.getMovieId()));
        projection.setRoom(new Room(projectionDto.getRoomId()));
        projection.setPriceType(new Price(projectionDto.getPriceTypeId()));
        return ResponseEntity.ok(projectionService.create(projection));
    }
    @GetMapping("/projections/{branchId}")
    public ResponseEntity<List<Projection>> getProjectionsByBranch(@PathVariable String branchId) {
        List<Projection> projections = projectionService.findByBranchId(branchId);
        return ResponseEntity.ok(projections);
    }
    @GetMapping("/projections")
    public ResponseEntity<List<Projection>> getProjections(
            @RequestParam(required = false) String filterType,
            @RequestParam(required = false) String filterValue) {
        List<Projection> projections;
        if ("movie".equalsIgnoreCase(filterType)) {
            projections = projectionService.findByMovieId(filterValue);
        } else if ("branch".equalsIgnoreCase(filterType)) {
            projections = projectionService.findByBranchId(filterValue);
        } else {
            projections = projectionService.findAll();
        }
        System.out.println("Projections fetched: " + projections);
        return ResponseEntity.ok(projections);
    }
}