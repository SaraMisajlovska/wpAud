package mk.finki.ukim.wpaud;

import mk.finki.ukim.wpaud.model.Category;
import mk.finki.ukim.wpaud.model.Manufacturer;
import mk.finki.ukim.wpaud.model.Product;
import mk.finki.ukim.wpaud.model.enumerations.Role;
import mk.finki.ukim.wpaud.service.CategoryService;
import mk.finki.ukim.wpaud.service.ManufacturerService;
import mk.finki.ukim.wpaud.service.ProductService;
import mk.finki.ukim.wpaud.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class WpAudApplicationTests {


    MockMvc mockMvc;
    @Autowired
    UserService userService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @BeforeEach
    public void setup(WebApplicationContext context){
        this.mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
         initData();

    }

    private static Category c1;
    private static Manufacturer m1;
    private static boolean dataInitialized = false;

    private void initData(){
        if (!dataInitialized) {
            c1 = categoryService.create("c1", "c1");
            categoryService.create("c2", "c2");

            m1 = manufacturerService.save("m1", "m1").get();
            manufacturerService.save("m2", "m2");

            String user = "user";
            String admin = "admin";

            userService.register(user, user, user, user, user, Role.ROLE_USER);
            userService.register(admin, admin, admin, admin, admin, Role.ROLE_ADMIN);
            dataInitialized = true;
        }
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetProduct() throws Exception {

        MockHttpServletRequestBuilder productRequest = MockMvcRequestBuilders.get("/products");
        this.mockMvc.perform(productRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("products"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("bodyContent","products"))
                .andExpect(MockMvcResultMatchers.view().name("master-template"));

    }
    @Test
    public void testDeleteProduct() throws Exception {

        Product product = this.productService.save("test", 2.0, 2, c1.getId(), m1.getId()).get();
        MockHttpServletRequestBuilder productDeleteRequest = MockMvcRequestBuilders
                .delete("/products/delete/"+product.getId());
        this.mockMvc.perform(productDeleteRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/products"));


    }

}
