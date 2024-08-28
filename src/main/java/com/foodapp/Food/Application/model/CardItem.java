package com.foodapp.Food.Application.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long CardItemId;

    @JoinColumn
    @ManyToOne
    public Cart card;

    @ManyToOne
    private  Food food;

    private int quantity;

    private List<String> ingredent;

    private Long total_price;


}
