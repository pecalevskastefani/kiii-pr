package mk.ukim.finki.wpaud.repository;

import mk.ukim.finki.wpaud.model.Product;
import mk.ukim.finki.wpaud.model.views.ProductsPerCategoryView;
import mk.ukim.finki.wpaud.model.views.ProductsPerManufacturerView;
import mk.ukim.finki.wpaud.repository.views.ProductsPerManufacturerViewRepository;
import mk.ukim.finki.wpaud.repository.views.ProductsPerCategoryViewRepository;
import mk.ukim.finki.wpaud.repository.views.ProductsPerManufacturerViewRepository;
import mk.ukim.finki.wpaud.service.CategoryService;
import mk.ukim.finki.wpaud.service.ManufacturerService;
import mk.ukim.finki.wpaud.service.ProductService;
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
    private ProductsPerManufacturerViewRepository productPerManufacturerViewRepository;
    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @Test
    public void testCreateNewProduct() {
        List<ProductsPerManufacturerView> list1 = this.productPerManufacturerViewRepository.findAll();
        Product product = new Product();
        product.setName("Ski Jacket 557");
        product.setManufacturer(this.manufacturerService.findAll().get(0));
        product.setCategory(this.categoryService.listCategories().get(0));
        this.productService.save(product.getName(), product.getPrice(), product.getQuantity(), product.getCategory().getId(),
                product.getManufacturer().getId());
        List<ProductsPerManufacturerView> list2 = this.productPerManufacturerViewRepository.findAll();
    }


}
