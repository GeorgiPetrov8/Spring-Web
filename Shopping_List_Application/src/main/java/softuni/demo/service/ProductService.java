package softuni.demo.service;

import softuni.demo.model.CategoryName;
import softuni.demo.model.service.ProductServiceModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void addProduct(ProductServiceModel productServiceModel);

    BigDecimal getTotalPrice();

    List<ProductServiceModel> findAllProductsByCategoryName(CategoryName categoryName);

    void buyById(String id);

    void buyAll();
}
