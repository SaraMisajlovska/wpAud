package mk.finki.ukim.wpaud.model;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Data
@Entity

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private Integer quantity;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Manufacturer manufacturer;

    public Product(String name, Double price, Integer quantity, Category category, Manufacturer manufacturer) {
        //bad practice; will have to be changed when we have a real db
        //this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.manufacturer = manufacturer;
    }

    public Product() {}
}
