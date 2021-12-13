package mk.finki.ukim.wpaud.bootstrap;

import mk.finki.ukim.wpaud.model.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Category> categories = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts = new ArrayList<>();

    @PostConstruct
    public void init() {
//        //CATEGORIES
//        categories.add(new Category("Movies", "Movies category"));
//        categories.add(new Category("Software", "Software category"));
//        Category category = new Category("Sport", "Sport Category");
//        categories.add(category);
//
//        //USERS
//        users.add(new User("sara.m", "sm", "sara", "misajlovska"));
//        users.add(new User("mara.m", "sm", "mara", "mararara"));
//
//        //MANUFACTURERS
//        Manufacturer manufacturer = new Manufacturer("Nike", "NY NY 25");
//        manufacturers.add(manufacturer);
//
//        //PRODUCTS
//        products.add(new Product("Ball1",235.7,7,category,manufacturer));
//        products.add(new Product("Ball2",235.7,7,category,manufacturer));
//        products.add(new Product("Ball3",235.7,7,category,manufacturer));

    }
}
