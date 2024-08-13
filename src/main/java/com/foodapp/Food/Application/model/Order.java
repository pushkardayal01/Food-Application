package com.foodapp.Food.Application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long OrderId;

    private User customer;

    @JsonIgnore
    @ManyToOne
    private Resturant resturant;

    private Long totalamount;

    private Date createdAt;

    @OneToMany
    private Address deliveryaddres;

    @OneToMany
    private List<OrderItem> item;

    private int totalItem;

    private int totalprice;

    //private Payment payment
}
