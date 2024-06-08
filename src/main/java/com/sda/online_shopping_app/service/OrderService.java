package com.sda.online_shopping_app.service;

import com.sda.online_shopping_app.entity.Order;
import com.sda.online_shopping_app.exceptions.OrderNotFoundException;
import com.sda.online_shopping_app.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    // Create a new order
    public Order save(Order order) {
        return orderRepo.save(order);
    }

    // Retrieve an order by its ID
    public Order findById(Integer id) {
        return orderRepo.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found: " + id));
    }

    // Update an existing order
    public Order updateOrder(Integer id, Order orderDetails) {
        Order existingOrder = orderRepo.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found: " + id));

        // Update the fields
        existingOrder.setUserName(orderDetails.getUserName());
        existingOrder.setTotalCost(orderDetails.getTotalCost());
        existingOrder.setDeliveryaddress(orderDetails.getDeliveryaddress());
        existingOrder.setUserAddress(orderDetails.getUserAddress());
        existingOrder.setDateOfSubmission(orderDetails.getDateOfSubmission());
        existingOrder.setStatus(orderDetails.getStatus());
        existingOrder.setUser(orderDetails.getUser());
        existingOrder.setOrderLine_id(orderDetails.getOrderLine_id());

        return orderRepo.save(existingOrder);
    }

    // Delete an order
    public void delete(Order order) {
        orderRepo.delete(order);
    }

    // List all orders
    public List<Order> findAll() {
        return orderRepo.findAll();
    }
}
