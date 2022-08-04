package com.example.thi_md3_c04.service;

import com.example.thi_md3_c04.DAO.ProductRepository;
import com.example.thi_md3_c04.model.Category;
import com.example.thi_md3_c04.model.Product;

import java.util.ArrayList;

public class ProductService {
    private final ProductRepository productRepository = new ProductRepository();

    public void createProduct(Product product) {
        productRepository.createProduct(product);
    }

    public void updateProduct(Product product) {
        productRepository.updateProduct(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteProductById(id);
    }

    public ArrayList<Product> findAllProducts() {
        return productRepository.findAllProducts();
    }

    public ArrayList<Category> findAllCategories() {
        return productRepository.findAllCategories();
    }

    public Product findProductById(int id) {
        return productRepository.findProductById(id);
    }

    public Category findCategoryById(int id) {
        return productRepository.findCategoryById(id);
    }

    public ArrayList<Product> searchProductsByName(String name) {
        return productRepository.searchProductsByName(name);
    }
}
