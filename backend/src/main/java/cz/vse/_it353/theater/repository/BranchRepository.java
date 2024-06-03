package cz.vse._it353.theater.repository;

import cz.vse._it353.theater.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Branch entity.
 */
public interface BranchRepository extends JpaRepository<Branch, String> {
    /**
     * Finds a Branch by its ID.
     * @param id the ID of the Branch
     * @return an Optional containing the Branch if found, or empty if not found
     */
    @Override
    Optional<Branch> findById(String id);

    /**
     * Finds all Branch entities.
     * @return a list of all Branch entities
     */
    @Override
    List<Branch> findAll();
}