package cz.vse._it353.theater.service;

import cz.vse._it353.theater.entity.Price;
import cz.vse._it353.theater.repository.PriceTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing Price entities.
 */
@Service
@AllArgsConstructor
public class PriceTypeService {
    private final PriceTypeRepository priceTypeRepository;

    /**
     * Retrieves all prices with pagination support.
     * @param page the pagination information
     * @param limit the limit of pagination
     * @return a paginated list of prices
     */
    public List<Price> findAll(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return priceTypeRepository.findAll(pageable).stream().toList();
    }

    public Price create(Price price) {
        return priceTypeRepository.save(price);
    }

    /**
     * Updates an existing price.
     * @param id the ID of the price to update
     * @param price the updated price entity
     * @return the updated price
     */
    public Price updatePrice(String id, Price price) {
        Price priceToUpdate = priceTypeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Price not found"));
        priceToUpdate.setValue(price.getValue());
        priceToUpdate.setName(price.getName());
        priceToUpdate.setVersion(price.getVersion());
        return priceTypeRepository.save(priceToUpdate);
    }

    /**
     * Finds a price by its ID.
     * @param id the ID of the price
     * @return the price if found, or throws an exception if not found
     */
    public Price findById(String id) {
        return priceTypeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Price not found"));
    }

    /**
     * Deletes a price by its ID.
     * @param id the ID of the price to delete
     */
    public void deleteById(String id) {
        priceTypeRepository.deleteById(id);
    }
}
