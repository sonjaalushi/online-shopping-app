package com.sda.online_shopping_app.repo;

import com.sda.online_shopping_app.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {

}