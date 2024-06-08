package com.sda.online_shopping_app.service;


import com.sda.online_shopping_app.entity.OrderLine;
import com.sda.online_shopping_app.repo.OrderLineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderLineService {

    @Autowired
    private OrderLineRepo orderLineRepo;


    public OrderLine save(OrderLine orderLine) {
        return orderLineRepo.save(orderLine);
    }


}
