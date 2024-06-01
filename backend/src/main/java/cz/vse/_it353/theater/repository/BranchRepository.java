package cz.vse._it353.theater.repository;

import cz.vse._it353.theater.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, String> {
    @Override
    Optional<Branch> findById(String s);

    List<Branch> findAll();

}
