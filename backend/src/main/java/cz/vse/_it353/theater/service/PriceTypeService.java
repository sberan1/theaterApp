package cz.vse._it353.theater.service;

import cz.vse._it353.theater.entity.Price;
import cz.vse._it353.theater.repository.PriceTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PriceTypeService {
    private final PriceTypeRepository priceTypeRepository;

    public List<Price> findAll(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return priceTypeRepository.findAll(pageable).stream().toList();
    }

    public Price create(Price price) {
        return priceTypeRepository.save(price);
    }

    public Price updatePrice(String id, Price price) {
        Price priceToUpdate = priceTypeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Price not found"));
        priceToUpdate.setValue(price.getValue());
        priceToUpdate.setName(price.getName());
        priceToUpdate.setVersion(price.getVersion());
        return priceTypeRepository.save(priceToUpdate);
    }

    public Price findById(String id) {
        return priceTypeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Price not found"));
    }

    public void deleteById(String id) {
        priceTypeRepository.deleteById(id);
    }
}
