package cz.vse._it353.theater.controller;

import cz.vse._it353.theater.dto.ProjectionDto;
import cz.vse._it353.theater.entity.Movie;
import cz.vse._it353.theater.entity.Price;
import cz.vse._it353.theater.entity.Projection;
import cz.vse._it353.theater.entity.Room;
import cz.vse._it353.theater.repository.ProjectionRepository;
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
    private final ProjectionRepository projectionRepository;

    @PostMapping("/admin/projection")
    public ResponseEntity<Projection> addProjection(@RequestBody ProjectionDto projectionDto) {
        return ResponseEntity.ok(projectionService.create(projectionDto));
    }
    @GetMapping("/projections/{branchId}")
    public ResponseEntity<List<Projection>> getProjectionsByBranch(@PathVariable String branchId) {
        return ResponseEntity.ok(projectionService.findByBranchId(branchId));
    }
    @GetMapping("/projections")
    public ResponseEntity<List<Projection>> getProjections(
            @RequestParam(required = false) String filterType,
            @RequestParam(required = false) String filterValue,
            @RequestParam(defaultValue = "startTime") String sortBy,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer limit
    ) {
        return ResponseEntity.ok(projectionService.findAll(filterType, filterValue, sortBy, page, limit));
    }
    @GetMapping("/user/projection/{id}")
    public ResponseEntity<Projection> getProjectionById(@PathVariable String id) {
        return ResponseEntity.ok(projectionService.findById(id).orElseThrow());
    }

    @DeleteMapping("/admin/projection/{id}")
    public ResponseEntity<Void> deleteProjection(@PathVariable String id) {
        projectionService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/admin/projection/{id}")
    public ResponseEntity<List<Projection>> updateProjection(@PathVariable String id, @RequestBody ProjectionDto projectionDto) {
        return ResponseEntity.ok(projectionService.updateProjection(id, projectionDto));
    }
}
