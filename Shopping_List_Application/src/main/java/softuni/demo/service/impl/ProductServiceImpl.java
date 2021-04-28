package softuni.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.demo.model.CategoryName;
import softuni.demo.model.Product;
import softuni.demo.model.service.ProductServiceModel;
import softuni.demo.repository.ProductRepository;
import softuni.demo.service.CategoryService;
import softuni.demo.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addProduct(ProductServiceModel productServiceModel) {
        Product product = modelMapper.map(productServiceModel, Product.class);
        product.setCategory(categoryService.getCategoryByName(productServiceModel.getCategory()));
        productRepository.save(product);
    }

    @Override
    public BigDecimal getTotalPrice() {
        return productRepository.getTotalProductsPrice();
    }

    @Override
    public List<ProductServiceModel> findAllProductsByCategoryName(CategoryName categoryName) {
        return productRepository.findAllByCategory_Name(categoryName)
                .stream().map(product -> modelMapper.map(product, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void buyById(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public void buyAll() {
        productRepository.deleteAll();
    }
}
