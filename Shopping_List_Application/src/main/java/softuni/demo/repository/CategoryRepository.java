package softuni.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.demo.model.Category;
import softuni.demo.model.CategoryName;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
       Optional<Category> findByName(CategoryName categoryName);
}
