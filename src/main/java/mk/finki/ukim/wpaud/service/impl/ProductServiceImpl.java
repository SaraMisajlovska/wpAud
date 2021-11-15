package mk.finki.ukim.wpaud.service.impl;

import mk.finki.ukim.wpaud.model.Category;
import mk.finki.ukim.wpaud.model.Manufacturer;
import mk.finki.ukim.wpaud.model.Product;
import mk.finki.ukim.wpaud.model.exceptions.CategoryNotFoundException;
import mk.finki.ukim.wpaud.model.exceptions.ManufacturerNotFoundException;
import mk.finki.ukim.wpaud.repository.InMemoryCategoryRepository;
import mk.finki.ukim.wpaud.repository.InMemoryManufacturerRepository;
import mk.finki.ukim.wpaud.repository.InMemoryProductRepository;
import mk.finki.ukim.wpaud.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final InMemoryProductRepository inMemoryProductRepository;
    private final InMemoryCategoryRepository inMemoryCategoryRepository;
    private final InMemoryManufacturerRepository inMemoryManufacturerRepository;

    public ProductServiceImpl(InMemoryProductRepository inMemoryProductRepository, InMemoryCategoryRepository inMemoryCategoryRepository, InMemoryManufacturerRepository inMemoryManufacturerRepository) {
        this.inMemoryProductRepository = inMemoryProductRepository;
        this.inMemoryCategoryRepository = inMemoryCategoryRepository;
        this.inMemoryManufacturerRepository = inMemoryManufacturerRepository;
    }

    @Override
    public List<Product> findAll() {
        return inMemoryProductRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return inMemoryProductRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return inMemoryProductRepository.findByName(name);
    }

    @Override
    public Optional<Product> save(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {

        Category category = inMemoryCategoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        Manufacturer manufacturer = inMemoryManufacturerRepository
                .findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        return inMemoryProductRepository.save(name,price,quantity,category,manufacturer);


    }

    @Override
    public void deleteById(Long id) {
        inMemoryProductRepository.deleteById(id);
    }
}
