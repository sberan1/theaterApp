package cz.vse._it353.theater.controller;

import cz.vse._it353.theater.entity.Price;
import cz.vse._it353.theater.service.PriceTypeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for the price type-related endpoints.
 */
@RestController
@CrossOrigin
@AllArgsConstructor
public class PriceTypeController {
    private final PriceTypeService priceTypeService;

    /**
     * Handles adding a new price.
     * @param price the price entity containing price details
     */
    @PostMapping("/admin/price")
    public void addPrice(@RequestBody Price price) {
        priceTypeService.create(price);
    }

    /**
     * Handles getting all prices.
     * @param page  the page number for pagination, default is 0
     * @param limit the number of prices per page, default is 20
     * @return a list of prices
     */
    @GetMapping("/prices")
    public List<Price> getPrices(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer limit
    ) {
        return priceTypeService.findAll(page, limit);
    }

    /**
     * Handles updating a price.
     * @param id    the ID of the price to be updated
     * @param price the price entity containing updated price details
     * @return the updated price
     */
    @PutMapping("/admin/price/{id}")
    public Price updatePrice(@PathVariable String id, @RequestBody Price price) {
        return priceTypeService.updatePrice(id, price);
    }

    /**
     * Handles getting a price by its ID.
     * @param id the ID of the price
     * @return the price
     */
    @GetMapping("/price/{id}")
    public Price getPriceById(@PathVariable String id) {
        return priceTypeService.findById(id);
    }

    /**
     * Handles deleting a price by its ID.
     * @param id the ID of the price to be deleted
     */
    @DeleteMapping("/admin/price/{id}")
    public void deletePrice(@PathVariable String id) {
        priceTypeService.deleteById(id);
    }
}