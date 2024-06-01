package cz.vse._it353.theater.service;

import cz.vse._it353.theater.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceTypeRepository extends JpaRepository<Price, String> {
    List<Price> findAll();
}
