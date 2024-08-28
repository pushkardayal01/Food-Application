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
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long CardId;

    @OneToOne
    private User customer;

    private Long total;

    @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true)
    private List<CardItem> cardItemList;



}
