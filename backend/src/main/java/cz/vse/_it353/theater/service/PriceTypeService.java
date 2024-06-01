package cz.vse._it353.theater.service;

import cz.vse._it353.theater.entity.Price;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PriceTypeService {
    private final PriceTypeRepository priceTypeRepository;

    public List<Price> findAll() {
        return priceTypeRepository.findAll();
    }

    public Price create(Price price) {
        return priceTypeRepository.save(price);
    }
}
