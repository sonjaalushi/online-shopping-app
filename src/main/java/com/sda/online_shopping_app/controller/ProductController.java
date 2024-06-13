//package com.sda.online_shopping_app.controller;
//
//import com.sda.online_shopping_app.entity.Product;
//import com.sda.online_shopping_app.exceptions.ProductNotFoundExceptions;
//import com.sda.online_shopping_app.service.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//
//
//@Controller
//@RequestMapping("/products")
//public class ProductController {
//
//    @Autowired
//    private ProductService productService;
//
//    // Create a new product
//    @PostMapping("/create")
//    public ResponseEntity<Product> create(@RequestBody Product product) {
//        Product createdProduct = productService.save(product);
//        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
//    }
//
//    // Retrieve a product by ID
//    @GetMapping("/getById/{id}")
//    public ResponseEntity<Product> findById(@PathVariable Integer id) {
//        try {
//            Product product = productService.findById(id);
//            return new ResponseEntity<>(product, HttpStatus.OK);
//        } catch (ProductNotFoundExceptions e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
////     Retrieve all products
//    @GetMapping("/getAllProducts")
//    public ResponseEntity<List<Product>> findAll() {
//        List<Product> products = productService.findAll();
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }
//
//    @GetMapping("/getProducts")
//    public  String getProducts(Model model){
//       List<Product>products= productService.findAll();
//       model.addAttribute("prod",products);
//       return "home";
//    }
//
//
//    // Update an existing product
//    @PutMapping("/update/{id}")
//    public ResponseEntity<Product> update(@PathVariable Integer id, @RequestBody Product productDetails) {
//
//        try {
//            Product updatedProduct = productService.update(id, productDetails);
//            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
//
//        } catch (ProductNotFoundExceptions e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    // Delete a product by ID
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Integer id) {
//        try {
//            productService.deleteByid(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//
//        } catch (ProductNotFoundExceptions e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//
//    @GetMapping("/filterByName")
//    public ResponseEntity<List<Product>> filterByName(@RequestParam String name) {
//        List<Product> filteredProducts = productService.filterByName(name);
//        return new ResponseEntity<>(filteredProducts, HttpStatus.OK);
//    }
//
//
//}