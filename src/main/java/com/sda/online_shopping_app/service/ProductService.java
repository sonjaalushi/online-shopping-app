package com.sda.online_shopping_app.service;

import java.util.List;
import com.sda.online_shopping_app.entity.Product;
import com.sda.online_shopping_app.exceptions.ProductNotFoundExceptions;
import com.sda.online_shopping_app.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;


    public Product save(Product product) {
        return productRepo.save(product);
    }

    public void delete(Product product) {
        productRepo.delete(product);
    }

    public List<Product>getListProduct(){
       return productRepo.findAll();
    }
    public Product update(Product product, Integer id) {
        if (!productRepo.existsById(id)) {
            throw new ProductNotFoundExceptions("Produc not found with id: " + id);
        }
        Optional<Product> products1 = productRepo.findById(id);

        products1.get().setName(product.getName());
        products1.get().setProduct_id(product.getProduct_id());
        products1.get().setDescription(product.getDescription());
        products1.get().setCategoryEntity(product.getCategoryEntity());

        return productRepo.save(products1.get());

    }
}
