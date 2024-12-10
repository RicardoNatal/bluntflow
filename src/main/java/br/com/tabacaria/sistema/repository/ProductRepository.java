package br.com.tabacaria.sistema.repository;

import br.com.tabacaria.sistema.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE UPPER(p.brand) LIKE CONCAT('%', UPPER(:brand), '%')")
    List<Product> findProductsByBrand(@Param("brand") String brand);

    @Query("SELECT p FROM Product p WHERE p.price >= :initialPrice AND p.price <= :finalPrice")
    List<Product> findProductsByPriceRange(BigDecimal initialPrice, BigDecimal finalPrice);

    @Query("SELECT p FROM Product p WHERE UPPER(p.name) LIKE CONCAT('%', UPPER(:name), '%')")
    List<Product> findProductsByName(@Param("name") String name);
}
