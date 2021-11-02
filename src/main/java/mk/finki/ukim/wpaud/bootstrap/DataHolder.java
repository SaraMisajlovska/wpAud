package mk.finki.ukim.wpaud.bootstrap;

import mk.finki.ukim.wpaud.model.Category;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Category> categories = new ArrayList<>();

    @PostConstruct
    public void init() {
        categories.add(new Category("Sport", "Sport category"));
        categories.add(new Category("Software", "Software category"));
    }
}
