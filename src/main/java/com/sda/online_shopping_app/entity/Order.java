package com.sda.online_shopping_app.entity;

import com.sda.online_shopping_app.entity.Enum.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "order_")
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

    private Status status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne
    private OrderLine orderLine;


}