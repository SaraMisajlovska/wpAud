package mk.finki.ukim.wpaud.service;

import mk.finki.ukim.wpaud.bootstrap.DataHolder;
import mk.finki.ukim.wpaud.model.Category;
import mk.finki.ukim.wpaud.repository.InMemoryCategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public interface CategoryService {
    Category create(String name, String description);

    Category update(String name, String description);

    void delete(String name);

    List<Category> listCategories();

    List<Category> searchCategories(String searchText);

}
