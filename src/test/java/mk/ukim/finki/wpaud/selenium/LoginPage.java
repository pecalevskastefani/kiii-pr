package mk.ukim.finki.wpaud.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private WebElement username;
    private WebElement password;
    private WebElement submit;

    public static LoginPage openLogin(WebDriver driver) {
        //na driverot mu vikame da povika kon /login
        //znaci urlto ke e localhost:9999/login
        get(driver, "/login");
        System.out.println(driver.getCurrentUrl());//urloto na koe se naogjame
        //ja vrakjame login stranata
        //da ja inicijalizirame stranata,
        //pagefactory ni ovozmozuva da gi inicijalizirame elementite shto ni se kako svojstva (webeleement)
        //i avtomatski bara dali imeto na promenlivata go ima kako id
        return PageFactory.initElements(driver, LoginPage.class);

    }

    public static ProductsPage doLogin(WebDriver driver, LoginPage loginPage, String username, String password) {
        //za da se najavime
        loginPage.username.sendKeys(username);
        loginPage.password.sendKeys(password);
        loginPage.submit.click();
        //da vidime dali ke ne odnese na /products
        System.out.println(driver.getCurrentUrl());
        //ja vrakjame products stranata
        return PageFactory.initElements(driver, ProductsPage.class);
    }


    public static LoginPage logout(WebDriver driver) {
        get(driver, "/logout");
        return PageFactory.initElements(driver, LoginPage.class);
    }


}
