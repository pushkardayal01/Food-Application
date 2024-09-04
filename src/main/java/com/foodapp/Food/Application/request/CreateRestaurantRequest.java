package com.foodapp.Food.Application.request;

import com.foodapp.Food.Application.model.Address;
import com.foodapp.Food.Application.model.ContactInformation;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateRestaurantRequest {

    private Long id;
    private String name;
    private String desrcription;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> image;

}
