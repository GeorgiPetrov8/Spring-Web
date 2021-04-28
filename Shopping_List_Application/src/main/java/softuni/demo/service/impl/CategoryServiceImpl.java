package softuni.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.demo.model.Category;
import softuni.demo.model.CategoryName;
import softuni.demo.repository.CategoryRepository;
import softuni.demo.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {
        if (categoryRepository.count() == 0) {
            Category drink = new Category(CategoryName.DRINK, "This is drink.");
            Category food = new Category(CategoryName.FOOD, "This is food.");
            Category houseHold = new Category(CategoryName.HOUSEHOLD, "This is houseHold.");
            Category other = new Category(CategoryName.OTHER, "This is other category.");

            categoryRepository.saveAll(List.of(drink, food, houseHold, other));
        }
    }

    @Override
    public Category getCategoryByName(CategoryName categoryName) {
        return categoryRepository.findByName(categoryName).orElse(null);
    }
}
