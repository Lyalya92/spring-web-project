package market_autumn.repositories;

import market_autumn.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Query("select p from Product p where p.amount > 0")
    List<Product> findAllByAmountNotNull();

    @Query("select p from Product p where p.price >= :min and p.price <= :max")
    List<Product> findAllByPriceBetween(Integer min, Integer max);

    @Query("select p from Product p where p.price >= :minPrice")
    List<Product> findAllByPriceMoreThan(Integer minPrice);

    @Query("select p from Product p where p.price <= :maxPrice")
    List<Product> findAllByPriceLessThan(Integer maxPrice);

    boolean existsProductByTitle(String title);

    boolean existsProductById(Long id);
}
