package mk.ukim.finki.wpaud.selenium;

import mk.ukim.finki.wpaud.model.Category;
import mk.ukim.finki.wpaud.model.Manufacturer;
import mk.ukim.finki.wpaud.model.enumerations.Role;
import mk.ukim.finki.wpaud.model.User;
import mk.ukim.finki.wpaud.service.CategoryService;
import mk.ukim.finki.wpaud.service.ManufacturerService;
import mk.ukim.finki.wpaud.service.ProductService;
import mk.ukim.finki.wpaud.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test") //go aktivirame profilot za so h2 bazata da rabotime
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {
    //potrebni ni se servisite
    @Autowired
    UserService userService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    CategoryService categoryService;


    @Autowired
    ProductService productService;

    //koga pravime selenium test ni treba driver
    private HtmlUnitDriver driver;

    //gi koristime za da kreirame produkti
    private static Category c1;
    private static Category c2;
    private static Manufacturer m1;
    private static Manufacturer m2;
    private static User regularUser;
    private static User adminUser;

    private static String user = "user";
    private static String admin = "admin";

    private static boolean dataInitialized = false;


    //pred sekoe od testovite
    //i driverot treba da se inicijalizira
    @BeforeEach
    private void setup() {
        this.driver = new HtmlUnitDriver(true);
        initData();
    }

    //posle sekoj test se povikuva destroy
    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close(); //gi cisti resursite
        }
    }

    private void initData() {
        if (!dataInitialized) {
            c1 = categoryService.create("c1", "c1");
            c2 = categoryService.create("c2", "c2");

            m1 = manufacturerService.save("m1", "m1").get();
            m2 = manufacturerService.save("m2", "m2").get();


            regularUser = userService.register(user, user, user, user, user, Role.ROLE_USER);
            adminUser = userService.register(admin, admin, admin, admin, admin, Role.ROLE_ADMIN);
            dataInitialized = true;
        }
    }

    @Test
    public void testScenario() throws Exception {
        ProductsPage productsPage = ProductsPage.to(this.driver);//inicijalno odime na productpage
        //inicijalno ocekuvame 0 produkti,0 kopcinja za birsenje edit cartbuttons i addbuttons
        productsPage.assertElemts(0, 0, 0, 0, 0);
        //ni vrakja login page
        LoginPage loginPage = LoginPage.openLogin(this.driver);

        //probuvame da se najavime
        productsPage = LoginPage.doLogin(this.driver, loginPage, adminUser.getUsername(), admin);
        //sega ocekuvame kako admin pak da nema podatoci no da se gleda kopceto za dodavanje
        productsPage.assertElemts(0, 0, 0, 0, 1);

        //kako admin dodavame produkt
        productsPage = AddOrEditProduct.addProduct(this.driver, "test", "100", "5", c2.getName(), m2.getName());
        //ocekuvame deka imame edna redica , gledame delete, gledame edit i gledame carbutton i add
        productsPage.assertElemts(1, 1, 1, 1, 1);

        //dodavame uste eden product
        productsPage = AddOrEditProduct.addProduct(this.driver, "test1", "200", "4", c1.getName(), m2.getName());
        productsPage.assertElemts(2, 2, 2, 2, 1);

        //тест на делете
        productsPage.getDeleteButtons().get(1).click();
        productsPage.assertElemts(1, 1, 1, 1, 1);

        //test na edit
        productsPage = AddOrEditProduct.editProduct(this.driver, productsPage.getEditButtons().get(0), "test1", "200", "4", c1.getName(), m2.getName());
        productsPage.assertElemts(1, 1, 1, 1, 1);

        //se odlogirame
        loginPage = LoginPage.logout(this.driver);

        //se najavuvame kako obicen user
        productsPage = LoginPage.doLogin(this.driver, loginPage, regularUser.getUsername(), user);
        //ocekuvame 1 product i add to cart, drugo ne gledame
        productsPage.assertElemts(1, 0, 0, 1, 0);

        //klikame na add to cart kopceto
        productsPage.getCartButtons().get(0).click();
        //dali se naogjame na urlot so treba
        Assert.assertEquals("url do not match", "http://localhost:9999/shopping-cart", this.driver.getCurrentUrl());

        //ocekuvame da sme na stranicava
        ShoppingCartPage cartPage = ShoppingCartPage.init(this.driver);
        //i ocekuvame 1 element
        cartPage.assertElemts(1);

    }


}
