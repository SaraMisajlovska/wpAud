package mk.finki.ukim.wpaud.repository;

import mk.finki.ukim.wpaud.model.views.ProductsPerCategoryView;
import mk.finki.ukim.wpaud.repository.views.ProductsPerCategoryViewRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsPerCategoryViewsRepositoryTest {
    @Autowired
    private ProductsPerCategoryViewRepository productsPerCategoryViewRepository;

    @Test
    public void testFindAll() {
        List<ProductsPerCategoryView> list = this.productsPerCategoryViewRepository.findAll();
        Assert.assertEquals(1, list.size());
    }

}
