package mk.finki.ukim.wpaud.model.dto;

import lombok.Data;
import mk.finki.ukim.wpaud.model.Category;
import mk.finki.ukim.wpaud.model.Manufacturer;

@Data
public class ProductDto {


    private String name;

    private Double price;

    private Integer quantity;

    private Category category;

    private Manufacturer manufacturer;

    public ProductDto() {
    }

    public ProductDto(String name, Double price, Integer quantity, Category category, Manufacturer manufacturer) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.manufacturer = manufacturer;
    }
}
