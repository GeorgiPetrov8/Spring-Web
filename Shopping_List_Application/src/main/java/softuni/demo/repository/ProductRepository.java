package softuni.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.demo.model.CategoryName;
import softuni.demo.model.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("SELECT SUM(p.price) FROM Product p")
    BigDecimal getTotalProductsPrice();

    List<Product> findAllByCategory_Name(CategoryName categoryName);
}
