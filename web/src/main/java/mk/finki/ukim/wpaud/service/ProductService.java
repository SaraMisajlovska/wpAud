package mk.finki.ukim.wpaud.service;

import mk.finki.ukim.wpaud.model.Product;
import mk.finki.ukim.wpaud.model.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Optional<Product> findByName(String name);

    Optional<Product> save(String name, Double price, Integer quantity,
                           Long categoryId, Long manufacturerId);

    Optional<Product> save(ProductDto productDto);

    Optional<Product> edit(Long id, String name, Double price, Integer quantity, Long category, Long manufacturer);

    Optional<Product> edit(Long id, ProductDto productDto);

    void deleteById(Long id);

    void refreshMaterializedView();
}
