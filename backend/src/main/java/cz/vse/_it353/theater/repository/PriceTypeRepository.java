package cz.vse._it353.theater.repository;

import cz.vse._it353.theater.entity.Price;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Price entity.
 */
public interface PriceTypeRepository extends JpaRepository<Price, String> {
    /**
     * Finds all Price entities with pagination support.
     * @param pageable the pagination information
     * @return a paginated list of Price entities
     */
    Page<Price> findAll(Pageable pageable);
}