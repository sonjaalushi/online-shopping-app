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

    public Product save(Product product) {
        return productRepo.save(product);
    }

    public Product findById(Integer id) {
        return productRepo.findById(id).orElseThrow(() ->
                new ProductNotFoundExceptions("Product not found with id: " + id));
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public Product update(Integer id, Product productDetails) {
        Product product = findById(id);
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setAuthor(productDetails.getAuthor());
        product.setUrl(productDetails.getUrl());
        product.setCategory(productDetails.getCategory());
        product.setUser(productDetails.getUser());
        return productRepo.save(product);
    }

    public void deleteByid(Integer id) {
        productRepo.deleteById(id);
    }

    public List<Product> filterByName(String name) {
        return productRepo.findByName(name);
    }

}