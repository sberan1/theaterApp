package cz.vse._it353.theater.controller;

import cz.vse._it353.theater.dto.CreateBranchDto;
import cz.vse._it353.theater.entity.Branch;
import cz.vse._it353.theater.entity.Projection;
import cz.vse._it353.theater.service.BranchService;
import cz.vse._it353.theater.service.ProjectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for the branch-related endpoints.
 */
@RestController
@AllArgsConstructor
@CrossOrigin
public class BranchController {
    private final BranchService branchService;
    private final ProjectionService projectionService;

    /**
     * Handles getting the list of all branches.
     * @return list of all existing branches
     */
    @GetMapping("/branches")
    public ResponseEntity<List<Branch>> getBranches() {
        List<Branch> branches = branchService.findAll();
        return ResponseEntity.ok(branches);
    }

    /**
     * Handles adding a branch.
     * @param branch the branch DTO contaiting branch details
     * @return a response entity containing the branch creation response
     */
    @PostMapping("/admin/branch")
    public ResponseEntity<Branch> addBranch(@RequestBody CreateBranchDto branch) {
        return ResponseEntity.ok(branchService.create(branch));
    }

    /**
     * Handles getting a branch by ID.
     * @param id specific branch ID
     * @return a response entity containing the wished branch
     */
    @GetMapping("/branch/{id}")
    public ResponseEntity<Branch> getBranchById(@PathVariable String id) {
        return ResponseEntity.ok(branchService.findById(id));
    }

    /**
     * Handles updating an existing branch.
     * @param id specific branch ID
     * @param branch the branch DTO containing new branch details
     * @return a response entity containing the updated branch
     */
    @PutMapping("/admin/branch/{id}")
    public ResponseEntity<Branch> updateBranch(@PathVariable String id, @RequestBody CreateBranchDto branch) {
        return ResponseEntity.ok(branchService.updateBranch(id, branch));
    }
}
