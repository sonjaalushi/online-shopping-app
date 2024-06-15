package com.sda.online_shopping_app.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.sda.online_shopping_app.entity.Enum.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    private String name;

    private String lastname;

    private String email;

    private String password;

    private String city;

    private String avatar;

    private String address;


    @JsonEnumDefaultValue
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Order> orders;



}