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
    public List<Price> getPrices(){
        return priceTypeService.findAll();
    }
}
