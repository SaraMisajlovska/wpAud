package mk.finki.ukim.wpaud.service;

import mk.finki.ukim.wpaud.model.Manufacturer;
import mk.finki.ukim.wpaud.model.Product;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    List<Manufacturer> findAll();

    Optional<Manufacturer> findById(Long id);

    Optional<Manufacturer> save(String name, String address);

    boolean deleteById(Long id);


}
