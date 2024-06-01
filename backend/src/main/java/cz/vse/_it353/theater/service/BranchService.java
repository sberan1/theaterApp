package cz.vse._it353.theater.service;

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

    public Branch create(Branch branch) {
        return branchRepository.save(branch);
    }
}