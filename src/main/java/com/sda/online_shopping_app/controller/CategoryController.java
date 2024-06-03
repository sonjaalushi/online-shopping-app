package com.sda.online_shopping_app.controller;

import com.sda.online_shopping_app.entity.Category;
import com.sda.online_shopping_app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @PostMapping("/creatCategory")
    public Category createCategore(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable Integer id) {
        Category categoryUpdate = categoryService.update(category, id);

        return new ResponseEntity<>(categoryUpdate, HttpStatus.OK);
    }


    @GetMapping("/findCategory/{id}")
    public ResponseEntity<Optional<Category>> findCategory(@PathVariable Integer id) {
        Optional<Category> getCategory = categoryService.findById(id);
        return new ResponseEntity<>(getCategory, HttpStatus.OK);
    }

}