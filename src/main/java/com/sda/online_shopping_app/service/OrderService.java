package com.sda.online_shopping_app.service;


import com.sda.online_shopping_app.entity.Order;
import com.sda.online_shopping_app.exceptions.OrderNotFoundException;
import com.sda.online_shopping_app.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;


    public Order save(Order order) {
        return orderRepo.save(order);
    }

    public Optional<Order> findById(Integer id) {
        return Optional.of(orderRepo.findById(id)).orElseThrow(()
                -> new OrderNotFoundException("Not found: " + id));
    }

    public void delete(Order order) {
        orderRepo.delete(order);
    }


}
