package com.sda.online_shopping_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "order")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String UserName;
    private Integer totalCost;
    private String Deliveryaddress;
    private String userAddress;
    private LocalDate DateofSubmission;

    private OrderLine orderLine;
    private Client client;
}
