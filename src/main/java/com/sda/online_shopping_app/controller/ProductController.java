package com.sda.online_shopping_app.controller;

import com.sda.online_shopping_app.entity.Product;
import com.sda.online_shopping_app.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductsService productsService;


    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable Integer id) {
        Product product1 = productsService.update(product, id);

        return new ResponseEntity<>(product1, HttpStatus.OK);

    }
    @PostMapping("/createProduct")
    public Product create(@RequestBody Product product) {
        return productsService.save(product);
    }


    @DeleteMapping("/deleteProducts")
    public void del(@RequestBody Product product) {
        productsService.delete(product);
    }


}