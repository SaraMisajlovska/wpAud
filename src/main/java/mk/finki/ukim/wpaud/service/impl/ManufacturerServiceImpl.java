package mk.finki.ukim.wpaud.service.impl;

import mk.finki.ukim.wpaud.model.Manufacturer;
import mk.finki.ukim.wpaud.repository.impl.InMemoryManufacturerRepository;
import mk.finki.ukim.wpaud.repository.jpa.ManufacturerRepository;
import mk.finki.ukim.wpaud.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    //InMemoryManufacturerRepository inMemoryManufacturerRepository;
    ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return manufacturerRepository.findById(id);
    }

    @Override
    public Optional<Manufacturer> save(String name, String address) {
        return Optional.of(manufacturerRepository.save(new Manufacturer(name,address)));
    }

    @Override
    public void deleteById(Long id) {
         manufacturerRepository.deleteById(id);
    }
}
