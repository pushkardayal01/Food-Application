package com.foodapp.Food.Application.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Resturant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ResturantId;

    ;


}
