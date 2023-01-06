package mk.ukim.finki.wpaud.repository.views;

import mk.ukim.finki.wpaud.model.views.ProductsPerCategoryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface ProductsPerCategoryViewRepository extends JpaRepository<ProductsPerCategoryView, Long> {


}
