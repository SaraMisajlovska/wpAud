package mk.finki.ukim.wpaud.service.impl;

import mk.finki.ukim.wpaud.model.Manufacturer;
import mk.finki.ukim.wpaud.model.Product;
import mk.finki.ukim.wpaud.repository.InMemoryManufacturerRepository;
import mk.finki.ukim.wpaud.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    InMemoryManufacturerRepository inMemoryManufacturerRepository;

    public ManufacturerServiceImpl(InMemoryManufacturerRepository inMemoryManufacturerRepository) {
        this.inMemoryManufacturerRepository = inMemoryManufacturerRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return inMemoryManufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return inMemoryManufacturerRepository.findById(id);
    }

    @Override
    public Optional<Manufacturer> save(String name, String address) {
        return inMemoryManufacturerRepository.save(name, address);
    }

    @Override
    public void deleteById(Long id) {
        inMemoryManufacturerRepository.deleteById(id);
    }
}
