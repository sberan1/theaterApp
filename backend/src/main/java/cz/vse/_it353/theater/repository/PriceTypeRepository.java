package cz.vse._it353.theater.repository;

import cz.vse._it353.theater.entity.Price;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceTypeRepository extends JpaRepository<Price, String> {
    Page<Price> findAll(Pageable pageable);
}
