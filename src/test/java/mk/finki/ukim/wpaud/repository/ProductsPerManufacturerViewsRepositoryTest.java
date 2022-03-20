package mk.finki.ukim.wpaud.repository;



import mk.finki.ukim.wpaud.model.Product;
import mk.finki.ukim.wpaud.model.views.ProductsPerManufacturerView;
import mk.finki.ukim.wpaud.repository.views.ProductsPerManufacturerViewRepository;
import mk.finki.ukim.wpaud.service.CategoryService;
import mk.finki.ukim.wpaud.service.ManufacturerService;
import mk.finki.ukim.wpaud.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsPerManufacturerViewsRepositoryTest {
    @Autowired
    private ProductsPerManufacturerViewRepository productsPerManufacturerViewRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private ProductService productService;


    @Test
    public void testCreateNewProduct() {
        List<ProductsPerManufacturerView> viewList = this.productsPerManufacturerViewRepository.findAll();

        Product product = new Product();
        product.setName("Ski Pants");
        product.setManufacturer(manufacturerService.findAll().get(1));
        product.setCategory(categoryService.listCategories().get(1));

        this.productService.save(
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory().getId(),
                product.getManufacturer().getId());

        List<ProductsPerManufacturerView> viewList2 = this.productsPerManufacturerViewRepository.findAll();

    }

}
