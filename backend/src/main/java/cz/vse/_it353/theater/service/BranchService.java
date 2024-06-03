package cz.vse._it353.theater.service;

import cz.vse._it353.theater.dto.CreateBranchDto;
import cz.vse._it353.theater.entity.Branch;
import cz.vse._it353.theater.repository.BranchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing Branch entities.
 */
@Service
@AllArgsConstructor
public class BranchService {
    private final BranchRepository branchRepository;

    /**
     * Retrieves all Branch entities.
     * @return a list of all Branch entities
     */
    public List<Branch> findAll() {
        return branchRepository.findAll();
    }

    /**
     * Creates a new Branch entity.
     * @param branch the DTO containing branch details
     * @return the created Branch entity
     */
    public Branch create(CreateBranchDto branch) {
        Branch newBranch = Branch.builder()
                .name(branch.getName())
                .address(branch.getAddress())
                .build();
        return branchRepository.save(newBranch);
    }

    /**
     * Finds a Branch entity by its ID.
     *
     * @param id the ID of the Branch
     * @return the Branch entity if found
     */
    public Branch findById(String id) {
        return branchRepository.findById(id).orElseThrow();
    }

    /**
     * Updates an existing Branch entity.
     * @param id the ID of the Branch to update
     * @param branch the DTO containing updated branch details
     * @return the updated Branch entity
     */
    public Branch updateBranch(String id, CreateBranchDto branch) {
        Branch branchToUpdate = branchRepository.findById(id).orElseThrow();
        branchToUpdate.setName(branch.getName());
        branchToUpdate.setAddress(branch.getAddress());
        branchToUpdate.setVersion(branch.getVersion());
        return branchRepository.save(branchToUpdate);
    }
}