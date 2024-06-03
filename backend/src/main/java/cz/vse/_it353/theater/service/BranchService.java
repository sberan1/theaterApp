package cz.vse._it353.theater.service;

import cz.vse._it353.theater.dto.CreateBranchDto;
import cz.vse._it353.theater.entity.Branch;
import cz.vse._it353.theater.repository.BranchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BranchService {
    private final BranchRepository branchRepository;

    public List<Branch> findAll() {
        return branchRepository.findAll();
    }

    public Branch create(CreateBranchDto branch) {
        Branch newBranch = Branch.builder()
                .name(branch.getName())
                .address(branch.getAddress())
                .build();
        return branchRepository.save(newBranch);
    }

    public Branch findById(String id) {
        return branchRepository.findById(id).orElseThrow();
    }

    public Branch updateBranch(String id, CreateBranchDto branch) {
        Branch branchToUpdate = branchRepository.findById(id).orElseThrow();
        branchToUpdate.setName(branch.getName());
        branchToUpdate.setAddress(branch.getAddress());
        branchToUpdate.setVersion(branch.getVersion());
        return branchRepository.save(branchToUpdate);
    }
}
