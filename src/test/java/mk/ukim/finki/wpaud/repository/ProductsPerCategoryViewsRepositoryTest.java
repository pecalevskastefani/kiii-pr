package mk.ukim.finki.wpaud.repository;

import mk.ukim.finki.wpaud.model.views.ProductsPerCategoryView;
import mk.ukim.finki.wpaud.repository.views.ProductsPerCategoryViewRepository;
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
        Assert.assertEquals(2, list.size());
    }


}
