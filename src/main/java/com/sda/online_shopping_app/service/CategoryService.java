package com.sda.online_shopping_app.service;

import com.sda.online_shopping_app.entity.Category;
import com.sda.online_shopping_app.exceptions.CategoryNotFoundException;
import com.sda.online_shopping_app.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;

    public Category saveCategory(Category category) {
        return categoryRepo.save(category);
    }

    public void deleteCategory(Category category) {
        categoryRepo.delete(category);
    }

    public Optional<Category> findById(Integer id) {
        return Optional.of(categoryRepo.findById(id)).orElseThrow(()
                -> new CategoryNotFoundException("Category not found with this :" + id));
    }


    public Category update(Category category,Integer id){

        //kontrollohet nese ekziston objekti qe duam te bejme edit
        //nese nuk ekziston ne keto rreshta bejme throw nje error
            if (!categoryRepo.existsById(id)) {
                throw new CategoryNotFoundException("You are not able to update  " +
                        "this Category because it does not exist");
            }
            //nese ekziston e gjejme nga id ne databaze
            Optional<Category> categoryEntity = categoryRepo.findById(id);

            //pasi e gjejme i bejme update fushave perkatese
            categoryEntity.get().setName(category.getName());

            //pasi bejme get dhe set , bejme saveAndFlush objektin e ri te ndryshuar

             //rikthejme serish objektin e ndryshuar ose mund te rikthejme void
             return categoryRepo.save(categoryEntity.get());
        }


}