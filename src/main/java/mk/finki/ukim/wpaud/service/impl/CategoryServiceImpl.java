package mk.finki.ukim.wpaud.service.impl;

import mk.finki.ukim.wpaud.model.Category;
import mk.finki.ukim.wpaud.repository.jpa.CategoryRepository;
import mk.finki.ukim.wpaud.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    //private final InMemoryCategoryRepository categoryRepository;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(String name, String description) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException();
        }
        Category c = new Category(name, description);
        categoryRepository.save(c);
        return c;
    }

    @Override
    public Category update(String name, String description) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException();
        }
        Category c = new Category(name, description);
        categoryRepository.save(c);
        return c;
    }

    @Override
    public void delete(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException();
        }

        categoryRepository.deleteByName(name);
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> searchCategories(String searchText) {
        return categoryRepository.findAllByNameLike(searchText);
    }

    @Override
    public Optional<Category> save(String name, String description) {
        return Optional.of(categoryRepository.save(new Category(name,description)));
    }
}
