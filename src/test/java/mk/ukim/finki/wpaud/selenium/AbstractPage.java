package mk.ukim.finki.wpaud.selenium;

import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Getter
public class AbstractPage {
    protected WebDriver driver; //pogeneralna klsaa od httpunitdriver


    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }


    //pomosen metod koj ni pomaga da ja zememe apsolutnata pateka na nasiot server
    static void get(WebDriver driver, String relativeUrl) {
        //ke ja vrati prvata i ako ne postoi ke ja vrati vtorata
        String url = System.getProperty("geb.build.baseUrl", "http://localhost:9999") + relativeUrl;
        driver.get(url);
    }

}
