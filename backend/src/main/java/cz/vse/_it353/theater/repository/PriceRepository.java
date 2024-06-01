package cz.vse._it353.theater.repository;

import cz.vse._it353.theater.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceRepository extends JpaRepository<Price, String> {
   // Price findById(String branchId);
}