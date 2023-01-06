package mk.ukim.finki.wpaud.repository.views;


import mk.ukim.finki.wpaud.model.views.ProductsPerManufacturerView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ProductsPerManufacturerViewRepository extends JpaRepository<ProductsPerManufacturerView, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.products_per_manufacturers", nativeQuery = true)
    public void refreshMaterializedView();
}

