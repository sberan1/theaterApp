package cz.vse._it353.theater.repository;

import cz.vse._it353.theater.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, String> {
}