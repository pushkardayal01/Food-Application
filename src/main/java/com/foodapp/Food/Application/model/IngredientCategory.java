package com.foodapp.Food.Application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long IngredientCategoryId;

    private String name;

    @ManyToOne
    @JsonIgnore
    private Resturant resturant;

    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL)
    private List<IngredientsItem> ingredients = new ArrayList<>();


}
