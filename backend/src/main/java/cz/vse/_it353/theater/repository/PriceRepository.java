package cz.vse._it353.theater.repository;

import cz.vse._it353.theater.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Price entity.
 */
public interface PriceRepository extends JpaRepository<Price, String> {
   // Price findById(String branchId);
}