package com.sda.online_shopping_app.entity;

import com.sda.online_shopping_app.entity.Enum.CategoryType;
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
    @Column(name = "category_id")
    private Integer id;


    @Column(name="name")
    private String name;

    private CategoryType categoryType;


    @OneToMany(cascade= CascadeType.ALL, fetch= FetchType.EAGER ,mappedBy = "category")
    private List<Product> products;
}
