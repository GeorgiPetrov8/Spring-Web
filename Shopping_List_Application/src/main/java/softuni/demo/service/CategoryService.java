package softuni.demo.service;

import softuni.demo.model.Category;
import softuni.demo.model.CategoryName;

public interface CategoryService {
    void initCategories();
    Category getCategoryByName(CategoryName categoryName);
}
