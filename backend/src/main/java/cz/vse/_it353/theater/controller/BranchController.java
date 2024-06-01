package cz.vse._it353.theater.controller;

import cz.vse._it353.theater.entity.Branch;
import cz.vse._it353.theater.service.BranchService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
public class BranchController {
    private final BranchService branchService;

    @GetMapping("/branches")
    public ResponseEntity<List<Branch>> getBranches() {
        List<Branch> branches = branchService.findAll();
        return ResponseEntity.ok(branches);
    }

    @PostMapping("/branch")
    public ResponseEntity<Branch> addBranch(@RequestBody Branch branch) {
        return ResponseEntity.ok(branchService.create(branch));
    }
}