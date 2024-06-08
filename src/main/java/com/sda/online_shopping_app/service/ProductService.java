package com.sda.online_shopping_app.service;

import com.sda.online_shopping_app.entity.Product;
import com.sda.online_shopping_app.exceptions.ProductNotFoundExceptions;
import com.sda.online_shopping_app.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    // Create or save a product
    public Product save(Product product) {
        return productRepo.save(product);
    }

    // Retrieve a product by ID
    public Product findById(Integer id) {
        return productRepo.findById(id).orElseThrow(() ->
                new ProductNotFoundExceptions("Product not found with id: " + id));
    }

    // Retrieve all products
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    // Update an existing product
    public Product update(Integer id, Product productDetails) {
        Product product = findById(id); // This will throw exception if not found
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setAuthor(productDetails.getAuthor());
        product.setUrl(productDetails.getUrl());
        product.setCategoryEntity(productDetails.getCategoryEntity());
        product.setUser(productDetails.getUser());
        return productRepo.save(product);
    }

    // Delete a product by ID
    public void delete(Integer id) {
        Product product = findById(id); // This will throw exception if not found
        productRepo.delete(product);
    }
    public List<Product> filterByName(String name) {
        return productRepo.findByName(name);
    }


}
