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

@RestController
@AllArgsConstructor
@CrossOrigin
public class BranchController {
    private final BranchService branchService;
    private final ProjectionService projectionService;

    @GetMapping("/branches")
    public ResponseEntity<List<Branch>> getBranches() {
        List<Branch> branches = branchService.findAll();
        return ResponseEntity.ok(branches);
    }

    @PostMapping("/admin/branch")
    public ResponseEntity<Branch> addBranch(@RequestBody CreateBranchDto branch) {
        return ResponseEntity.ok(branchService.create(branch));
    }
}
