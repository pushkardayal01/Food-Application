package com.foodapp.Food.Application.response;

import com.foodapp.Food.Application.model.USER_ROLE;
import lombok.Data;


@Data
public class AuthResponse {


    private String jwt;
    private  String message;

    private USER_ROLE role;
}
