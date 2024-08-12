package com.foodapp.Food.Application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long FoodId;

    private String name;

    private String description;

    private List<String> ingredent;

    private Long price;

    @ManyToOne
    private Category foodCategory;

    @Column(length = 1000)
    private List<String> image;

    private boolean available;

    @ManyToOne
    private Resturant resturant;


    private boolean isvegitarian;
    private boolean isseasonal;


    private List<IngredientsItem> ingredientsItems = new ArrayList<>();



}
