package mk.finki.ukim.wpaud.service.impl;

import mk.finki.ukim.wpaud.model.Category;
import mk.finki.ukim.wpaud.model.Manufacturer;
import mk.finki.ukim.wpaud.model.Product;
import mk.finki.ukim.wpaud.model.dto.ProductDto;
import mk.finki.ukim.wpaud.model.events.ProductCreatedEvent;
import mk.finki.ukim.wpaud.model.exceptions.CategoryNotFoundException;
import mk.finki.ukim.wpaud.model.exceptions.ManufacturerNotFoundException;
import mk.finki.ukim.wpaud.model.exceptions.ProductNotFoundException;
import mk.finki.ukim.wpaud.repository.jpa.CategoryRepository;
import mk.finki.ukim.wpaud.repository.jpa.ManufacturerRepository;
import mk.finki.ukim.wpaud.repository.jpa.ProductRepository;
import mk.finki.ukim.wpaud.repository.views.ProductsPerManufacturerViewRepository;
import mk.finki.ukim.wpaud.service.ProductService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final ProductsPerManufacturerViewRepository productsPerManufacturerViewRepository;
    private final ApplicationEventPublisher aplicationEventPublisher;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              ManufacturerRepository manufacturerRepository,
                              ProductsPerManufacturerViewRepository productsPerManufacturerViewRepository, ApplicationEventPublisher aplicationEventPublisher) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.productsPerManufacturerViewRepository = productsPerManufacturerViewRepository;
        this.aplicationEventPublisher = aplicationEventPublisher;
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
        Product product = new Product(name, price, quantity, category, manufacturer);
        this.productRepository.save(product);
        //this.refreshMaterializedView();
        this.aplicationEventPublisher.publishEvent(new ProductCreatedEvent(product));
        return Optional.of(product);
    }

    @Override
    public Optional<Product> save(ProductDto productDto) {

        Category category = categoryRepository
                .findById(productDto.getCategory().getId())
                .orElseThrow(() -> new CategoryNotFoundException(productDto.getCategory().getId()));

        Manufacturer manufacturer = manufacturerRepository
                .findById(productDto.getManufacturer().getId())
                .orElseThrow(() -> new ManufacturerNotFoundException(productDto.getManufacturer().getId()));

        productRepository.deleteByName(productDto.getName());
        Product product = new Product(productDto.getName(), productDto.getPrice(), productDto.getQuantity(), category, manufacturer);
        productRepository.save(product);
        //this.refreshMaterializedView();
        this.aplicationEventPublisher.publishEvent(new ProductCreatedEvent(product));
        return Optional.of(product);
    }

    @Override
    @Transactional
    public Optional<Product> edit(Long id, String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {

        Product product = this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        product.setCategory(category);

        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));
        product.setManufacturer(manufacturer);

        productRepository.save(product);
        //this.refreshMaterializedView();
        this.aplicationEventPublisher.publishEvent(new ProductCreatedEvent(product));
        return Optional.of(product);
    }

    @Override
    public Optional<Product> edit(Long id, ProductDto productDto) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());

        Category category = this.categoryRepository.findById(productDto.getCategory().getId())
                .orElseThrow(() -> new CategoryNotFoundException(productDto.getCategory().getId()));
        product.setCategory(category);

        Manufacturer manufacturer = this.manufacturerRepository.findById(productDto.getManufacturer().getId())
                .orElseThrow(() -> new ManufacturerNotFoundException(productDto.getManufacturer().getId()));
        product.setManufacturer(manufacturer);

        productRepository.save(product);
        //this.refreshMaterializedView();
        this.aplicationEventPublisher.publishEvent(new ProductCreatedEvent(product));
        return Optional.of(product);
    }


    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void refreshMaterializedView() {
        this.productsPerManufacturerViewRepository.refreshMaterializedView();
    }
}
