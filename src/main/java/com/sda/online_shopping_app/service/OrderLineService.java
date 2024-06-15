package com.sda.online_shopping_app.service;

import java.util.List;
import com.sda.online_shopping_app.entity.OrderLine;
import com.sda.online_shopping_app.repo.OrderLineRepo;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderLineService {

    @Autowired
    private OrderLineRepo orderLineRepo;




    public OrderLine updateOrderLine(Integer id, OrderLine updatedOrderLine) {
        OrderLine orderLine = orderLineRepo.findById(id).orElse(null);
        if (orderLine != null) {
            orderLine.setProduct(updatedOrderLine.getProduct());
            orderLine.setNumberOfProducts(updatedOrderLine.getNumberOfProducts());
            orderLine.setProductPrice(updatedOrderLine.getProductPrice());
            orderLine.setOrder(updatedOrderLine.getOrder());
            return orderLineRepo.save(orderLine);
        }
        return null;
    }



    public OrderLine save(OrderLine orderLine) {
        return orderLineRepo.save(orderLine);
    }

public void deleteByID(Integer id){
        orderLineRepo.deleteById(id);
}

    public Optional<OrderLine> findByID(Integer id){
        return orderLineRepo.findById(id);
    }
    public List<OrderLine>getAll(){
        return orderLineRepo.findAll();
    }
}
