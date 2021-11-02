package mk.finki.ukim.wpaud.repository;

import mk.finki.ukim.wpaud.bootstrap.DataHolder;
import mk.finki.ukim.wpaud.model.Category;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryCategoryRepository {
    //findAll save delete search findByName

    public List<Category> findAll() {
        return DataHolder.categories;
    }

    public Category save(Category c) {
        if (c == null || c.getName() == null || c.getName().isBlank()) {
            return null;
        }
        DataHolder.categories.removeIf(e -> e.getName().equals(c.getName()));
        DataHolder.categories.add(c);
        return c;
    }

    public Optional<Category> findByName(String name) {
        return DataHolder.categories
                .stream()
                .filter(e -> e.getName().equals(name))
                .findFirst();
    }

    public List<Category> search(String text) {
        return DataHolder.categories
                .stream()
                .filter(e -> e.getName().contains(text) || e.getDescription().contains(text))
                .collect(Collectors.toList());
    }

    public void delete(String name) {
        if (name == null) {
            return;
        }
        DataHolder.categories.removeIf(e -> e.getName().equals(name));
    }

}
