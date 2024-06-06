package com.sda.online_shopping_app.controller;


import com.sda.online_shopping_app.entity.Order;
import com.sda.online_shopping_app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/createOrder")
    public Order create(@RequestBody Order order) {
        return orderService.save(order);
    }

    @GetMapping("/findOrderId/{id}")
    public ResponseEntity<Optional<Order>> findById(@RequestParam Integer id) {

        Optional<Order> orderFind = orderService.findById(id);
        return new ResponseEntity<>(orderFind, HttpStatus.OK);
    }

    @DeleteMapping("/deleteOrder")
    public void delete(@RequestBody Order order) {
        orderService.delete(order);
    }


}