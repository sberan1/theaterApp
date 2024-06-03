package cz.vse._it353.theater.controller;

import cz.vse._it353.theater.entity.Price;
import cz.vse._it353.theater.service.PriceTypeService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class PriceTypeController {
    private final PriceTypeService priceTypeService;

    @PostMapping("/admin/price")
    public void addPrice(@RequestBody Price price){
        priceTypeService.create(price);
    }

    @GetMapping("/prices")
    public List<Price> getPrices(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer limit
    ){
        return priceTypeService.findAll(page, limit);
    }

    @PutMapping("/admin/price/{id}")
    public Price updatePrice(@PathVariable String id, @RequestBody Price price){
        return priceTypeService.updatePrice(id, price);
    }

    @GetMapping("/price/{id}")
    public Price getPriceById(@PathVariable String id){
        return priceTypeService.findById(id);
    }

    @DeleteMapping("/admin/price/{id}")
    public void deletePrice(@PathVariable String id){
        priceTypeService.deleteById(id);
    }
}
