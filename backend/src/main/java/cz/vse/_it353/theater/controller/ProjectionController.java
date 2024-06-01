package cz.vse._it353.theater.controller;

import cz.vse._it353.theater.dto.ProjectionDto;
import cz.vse._it353.theater.entity.Projection;
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
            @RequestParam(required = false) String filterValue) {
        return ResponseEntity.ok(projectionService.getProjections(filterType, filterValue));
    }
}
