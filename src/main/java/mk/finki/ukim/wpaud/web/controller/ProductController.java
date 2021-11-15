package mk.finki.ukim.wpaud.web.controller;

import mk.finki.ukim.wpaud.model.Category;
import mk.finki.ukim.wpaud.model.Manufacturer;
import mk.finki.ukim.wpaud.model.Product;
import mk.finki.ukim.wpaud.service.CategoryService;
import mk.finki.ukim.wpaud.service.ManufacturerService;
import mk.finki.ukim.wpaud.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;

    public ProductController(ProductService productService, CategoryService categoryService, ManufacturerService manufacturerService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasErrors", true);
            model.addAttribute("error", error);
        }

        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    // /products/67 - path variable
    // /products?id=78 - request param
    // /{id} mora vo zagradi zatoa sto e varijabilen del i se menuva sekoj pat
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/add-form")
    public String addProductsPage(Model model) {
        List<Category> categories = categoryService.listCategories();
        List<Manufacturer> manufacturers = manufacturerService.findAll();

        model.addAttribute("categories", categories);
        model.addAttribute("manufacturers", manufacturers);
        return "addProduct";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductsPage(@PathVariable Long id, Model model) {
        if (productService.findById(id).isPresent()) {
            Product product = productService.findById(id).get();

            List<Category> categories = categoryService.listCategories();
            List<Manufacturer> manufacturers = manufacturerService.findAll();
            model.addAttribute("categories", categories);
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("product", product);
            return "addProduct";
        }
        return "redirect:/products?error=ProductNotFound";
    }


    @PostMapping("/add")
    public String saveProduct(@RequestParam String name,
                              @RequestParam Double price,
                              @RequestParam Integer quantity,
                              @RequestParam Long categoryId,
                              @RequestParam Long manufacturerId) {
        productService.save(name, price, quantity, categoryId, manufacturerId);
        return "redirect:/products";
    }


}
