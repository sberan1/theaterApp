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
}
