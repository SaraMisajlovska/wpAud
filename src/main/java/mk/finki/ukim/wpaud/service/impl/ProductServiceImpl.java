package mk.finki.ukim.wpaud.service.impl;

import mk.finki.ukim.wpaud.model.Category;
import mk.finki.ukim.wpaud.model.Manufacturer;
import mk.finki.ukim.wpaud.model.Product;
import mk.finki.ukim.wpaud.model.exceptions.CategoryNotFoundException;
import mk.finki.ukim.wpaud.model.exceptions.ManufacturerNotFoundException;
import mk.finki.ukim.wpaud.repository.jpa.CategoryRepository;
import mk.finki.ukim.wpaud.repository.jpa.ManufacturerRepository;
import mk.finki.ukim.wpaud.repository.jpa.ProductRepository;
import mk.finki.ukim.wpaud.service.ProductService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              ManufacturerRepository manufacturerRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Product> save(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {

        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        Manufacturer manufacturer = manufacturerRepository
                .findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        productRepository.deleteByName(name);
        return Optional.of(productRepository.save(new Product(name, price, quantity, category, manufacturer)));
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
