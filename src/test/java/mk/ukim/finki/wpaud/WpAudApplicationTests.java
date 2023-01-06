package mk.ukim.finki.wpaud;

import mk.ukim.finki.wpaud.model.Category;
import mk.ukim.finki.wpaud.model.Manufacturer;
import mk.ukim.finki.wpaud.model.Product;
import mk.ukim.finki.wpaud.model.enumerations.Role;
import mk.ukim.finki.wpaud.service.CategoryService;
import mk.ukim.finki.wpaud.service.ManufacturerService;
import mk.ukim.finki.wpaud.service.ProductService;
import mk.ukim.finki.wpaud.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
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
    //za da ja incijalizirame go kreirame dole metodot

    @Autowired
    UserService userService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    CategoryService categoryService;


    @Autowired
    ProductService productService;

    //kreirame staticki promenlivi za da ne se kreiraat celo vrmee podatocite posto beforeeach metodot
    //se povikuva za sekoj test posebno.
    private static Category c1;
    private static Manufacturer m1;
    private static boolean dataInitialized = false;


    //pred sekoj test ke se izvrsi ovoj metod
    @BeforeEach
    public void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        //go postavuvame contexot i povikuvame build()
        initData();
    }

    private void initData() {
        if (!dataInitialized) {//ako podatocite ne se inicijalizirani kreiraj
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
    public void testGetProducts() throws Exception {
        //da pobarame na relativna pateka na naseto url ona shto sakame da go povikame
        MockHttpServletRequestBuilder productRequest =
                MockMvcRequestBuilders.get("/products");
        //izvrsuvame akcija
        this.mockMvc.perform(productRequest)
                //pecatime razultat
                .andDo(MockMvcResultHandlers.print())
                //da proverime dali statusot e ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //dali postoi products vo modelot
                .andExpect(MockMvcResultMatchers.model().attributeExists("products"))
                //i bodyContent dali ima vrednost products
                .andExpect(MockMvcResultMatchers.model().attribute("bodyContent", "products"))
                //dali viewto sto se vrakja e master-template
                .andExpect(MockMvcResultMatchers.view().name("master-template"));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        //kreirame produkt
        Product product = this.productService.save("test", 2.0, 2, c1.getId(), m1.getId()).get();
        MockHttpServletRequestBuilder productDeleteRequest = MockMvcRequestBuilders
                .delete("/products/delete/" + product.getId());// urlot na koe ke se povika delete i idto na produktot

        this.mockMvc.perform(productDeleteRequest) //go povikuvame
                .andDo(MockMvcResultHandlers.print()) //dali e ok odgovorot
                //dali e statusot redirekcija
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                //i dali ne vrakja na products
                .andExpect(MockMvcResultMatchers.redirectedUrl("/products"));

    }

}
