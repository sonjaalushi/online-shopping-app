package com.sda.online_shopping_app.service;


import com.sda.online_shopping_app.entity.Products;
import com.sda.online_shopping_app.exceptions.ProductNotFoundExceptions;
import com.sda.online_shopping_app.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductsService {

    @Autowired
    ProductRepo productRepo;


    public Products save(Products products) {
        return productRepo.save(products);
    }

    public void delete(Products products) {
        productRepo.delete(products);
    }

    public Products update(Products products, Integer id) {
        if (!productRepo.existsById(id)) {
            throw new ProductNotFoundExceptions("Produc not found with id: " + id);
        }
        Optional<Products> products1 = productRepo.findById(id);

        products1.get().setName(products.getName());
        products1.get().setProduct_id(products.getProduct_id());
        products1.get().setDescription(products.getDescription());
        products1.get().setCategoryEntity(products.getCategoryEntity());

        return productRepo.save(products1.get());

    }
}
