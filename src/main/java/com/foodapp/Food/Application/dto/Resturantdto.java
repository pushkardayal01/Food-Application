package com.foodapp.Food.Application.dto;

import jakarta.persistence.Column;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class Resturantdto {

    private Long ResturantId;

    private String title;

    private String description;

    @Column(length=1000)
    private List<String> image;

}
