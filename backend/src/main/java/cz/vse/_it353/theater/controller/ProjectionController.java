package cz.vse._it353.theater.controller;

import cz.vse._it353.theater.dto.ProjectionDto;
import cz.vse._it353.theater.entity.Projection;
import cz.vse._it353.theater.repository.ProjectionRepository;
import cz.vse._it353.theater.service.ProjectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for the projection-related endpoints.
 */
@RestController
@AllArgsConstructor
@CrossOrigin
public class ProjectionController {
    private final ProjectionService projectionService;
    private final ProjectionRepository projectionRepository;

    /**
     * Handles adding a new projection.
     * @param projectionDto the projection DTO containing projection details
     * @return a response entity containing the new projection
     */
    @PostMapping("/admin/projection")
    public ResponseEntity<Projection> addProjection(@RequestBody ProjectionDto projectionDto) {
        return ResponseEntity.ok(projectionService.create(projectionDto));
    }

    /**
     * Handles getting projections by branch ID.
     * @param branchId the ID of the branch
     * @return a response entity containing a list of projections for the specified branch
     */
    @GetMapping("/projections/{branchId}")
    public ResponseEntity<List<Projection>> getProjectionsByBranch(@PathVariable String branchId) {
        return ResponseEntity.ok(projectionService.findByBranchId(branchId));
    }

    /**
     * Handles getting all projections with optional filters and sorting.
     * @param filterType the type of filter to apply
     * @param filterValue the value of the filter
     * @param sortBy the field to sort by, default is "startTime"
     * @param page the page number for pagination, default is 0
     * @param limit the number of projections per page, default is 20
     * @return a response entity containing a list of projections
     */
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

    /**
     * Handles getting a projection by its ID.
     * @param id the ID of the projection
     * @return a response entity containing the projection
     */
    @GetMapping("/user/projection/{id}")
    public ResponseEntity<Projection> getProjectionById(@PathVariable String id) {
        return ResponseEntity.ok(projectionService.findById(id).orElseThrow());
    }

    /**
     * Handles deleting a projection by its ID.
     * @param id the ID of the projection to be deleted
     * @return a response entity indicating the result of the delete operation
     */
    @DeleteMapping("/admin/projection/{id}")
    public ResponseEntity<Void> deleteProjection(@PathVariable String id) {
        projectionService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Handles updating a projection.
     * @param id the ID of the projection to be updated
     * @param projectionDto the projection DTO containing updated projection details
     * @return a response entity containing the updated projections
     */
    @PutMapping("/admin/projection/{id}")
    public ResponseEntity<List<Projection>> updateProjection(@PathVariable String id, @RequestBody ProjectionDto projectionDto) {
        return ResponseEntity.ok(projectionService.updateProjection(id, projectionDto));
    }
}