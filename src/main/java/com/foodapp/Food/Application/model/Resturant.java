package com.foodapp.Food.Application.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Resturant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ResturantId;

    private String name;

    @OneToOne
    private User owner;

    private String description;

    private String cuisingtype;

    @OneToOne
    private Address address;

    @Embedded
    private ContactInformation contactInformation;

    private String openinghour;

    @OneToMany(mappedBy = "resturant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orderlist;


    @ElementCollection
    @Column(length=1000)
    private List<String> images;


    private Date registrationdate;

    private  boolean open;

    @JsonIgnore
    @OneToMany(mappedBy = "resturant")
    private List<Food> food = new ArrayList<>();

}
