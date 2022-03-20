package mk.finki.ukim.wpaud.repository.views;

import mk.finki.ukim.wpaud.model.views.ProductsPerManufacturerView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ProductsPerManufacturerViewRepository extends JpaRepository<ProductsPerManufacturerView, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.products_per_manufacturer",
            nativeQuery = true)
    void refreshMaterializedView();
}