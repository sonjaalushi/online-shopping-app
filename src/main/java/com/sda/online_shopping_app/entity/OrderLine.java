package com.sda.online_shopping_app.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "orderline")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "orderLine", cascade = CascadeType.ALL)
    private List<Product> products;

    private Integer numberOfProducts;

    private Integer productPrice;

    @OneToOne(mappedBy = "orderLine", cascade = CascadeType.ALL)
    private Order order;

}