package com.sda.online_shopping_app.service;


import com.sda.online_shopping_app.entity.Products;
import com.sda.online_shopping_app.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {

    @Autowired
    ProductRepo productRepo;


    public Products save(Products products) {
       return productRepo.save(products);
    }

    public void delete(Products products){
         productRepo.delete(products);
    }
}
