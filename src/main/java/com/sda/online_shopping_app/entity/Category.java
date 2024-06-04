package com.sda.online_shopping_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="category")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer category_id;

    @Column(name="name")
    private String name;

    @OneToMany(cascade= CascadeType.ALL, fetch= FetchType.EAGER ,mappedBy = "categoryEntity")
    private List<Product> products;
}
