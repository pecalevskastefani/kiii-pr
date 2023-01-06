package mk.ukim.finki.wpaud.bootstrap;

import mk.ukim.finki.wpaud.model.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component //za istanca da se kreira koga ke se startyva aplikacijata
public class DataHolder {
    public static List<Category> categories = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts = new ArrayList<>();

    /*@PostConstruct//sam da se povika koa se startuva
    public void init(){
        categories.add(new Category("Books","Books Category"));
        categories.add(new Category("Movies","Movies Category"));

        users.add(new User("kostadin.mishev","km","Kostadin","Mishev"));
        users.add(new User("riste,stojanov","rs","Riste","Stojanov"));

        Manufacturer manufacturer= new Manufacturer("Nike","NY NY");
        Manufacturer manufacturer2= new Manufacturer("Addias","28 bNY");
        manufacturers.add(manufacturer);
        manufacturers.add(manufacturer2);

        Category category= new Category("Sport","Sport category");
        categories.add(category);
        products.add(new Product("Ball 1",234.7,7,category,manufacturer));
        products.add(new Product("Ball 2",234.7,7,category,manufacturer));
        products.add(new Product("Ball 3",234.7,7,category,manufacturer));
    }*/
}
