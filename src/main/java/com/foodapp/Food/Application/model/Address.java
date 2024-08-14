package com.foodapp.Food.Application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long AddressId;

    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    // Relationship with User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Relationship with Order
    @OneToMany(mappedBy = "deliveryaddres")
    private List<Order> orders; // Ensure this is a collection type
    // Relationship with Resturant
    @ManyToOne
    private Resturant resturant;
}
