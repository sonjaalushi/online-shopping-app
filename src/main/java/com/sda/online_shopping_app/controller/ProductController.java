package com.sda.online_shopping_app.controller;

import com.sda.online_shopping_app.entity.Products;
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
    public ResponseEntity<Products> update(@RequestBody Products products, @PathVariable Integer id) {
        Products products1 = productsService.update(products, id);

        return new ResponseEntity<>(products1, HttpStatus.OK);

    }
    @PostMapping("/createProduct")
    public Products create(@RequestBody Products product) {
        return productsService.save(product);
    }


    @DeleteMapping("/deleteProducts")
    public void del(@RequestBody Products products) {
        productsService.delete(products);
    }


}