package cz.vse._it353.theater.controller;

import cz.vse._it353.theater.entity.Projection;
import cz.vse._it353.theater.service.ProjectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/projections")
public class ProjectionController {
    private final ProjectionService projectionService;

    @GetMapping("/branches/{branchId}")
    public ResponseEntity<List<Projection>> getProjectionsByBranch(@PathVariable String branchId) {
        List<Projection> projections = projectionService.findByBranchId(branchId);
        return ResponseEntity.ok(projections);
    }

    @PostMapping("/projections")
    public ResponseEntity<Projection> addProjection(@RequestBody Projection projection) {
        return ResponseEntity.ok(projectionService.create(projection));
    }
}