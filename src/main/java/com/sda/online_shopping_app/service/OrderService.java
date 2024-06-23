package com.sda.online_shopping_app.service;

import com.sda.online_shopping_app.entity.Enum.Status;
import com.sda.online_shopping_app.entity.Order;
import com.sda.online_shopping_app.entity.OrderLine;
import com.sda.online_shopping_app.entity.Product;
import com.sda.online_shopping_app.entity.UserEntity;
import com.sda.online_shopping_app.exceptions.OrderNotFoundException;
import com.sda.online_shopping_app.repo.OrderRepo;
import com.sda.online_shopping_app.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProductRepo productRepo;

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

    public void createOrder(UserEntity user, Integer productId) {
        // Get the product by id
        Product product = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        // Create a new order
        Order order = new Order();
        order.setUser(user);
        order.setUserName(user.getName());
        order.setDateOfSubmission(LocalDate.now());
        order.setStatus(Status.PENDING);

        // Add the order line to the order
        OrderLine orderLine = new OrderLine();
        orderLine.setProduct(product);
        orderLine.setOrder(order);
        order.getOrderLine_id().add(orderLine);

        // Set the total cost (assuming the product has a price)
//        order.setTotalCost(product.getName());

        // Save the order
        orderRepo.save(order);
    }
    // Delete an order
    public void delete(Integer id) {
        orderRepo.deleteById(id);
    }



    // List all orders
    public List<Order> findAll() {
        return orderRepo.findAll();
    }
}
