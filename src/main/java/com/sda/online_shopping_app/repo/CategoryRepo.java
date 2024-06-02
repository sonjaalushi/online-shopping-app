package com.sda.online_shopping_app.repo;

import com.sda.online_shopping_app.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {


    @Query("SELECT b FROM Category b WHERE b.name = :name")
    List<Category> findCategoryByAuthorName(@Param("name") String name);





}