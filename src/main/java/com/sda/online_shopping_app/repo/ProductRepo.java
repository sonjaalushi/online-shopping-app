package com.sda.online_shopping_app.repo;

import com.sda.online_shopping_app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {


    List<Product> findByName(String name);



}
