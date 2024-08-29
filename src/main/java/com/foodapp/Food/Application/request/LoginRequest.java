package com.foodapp.Food.Application.request;

import lombok.Data;
import org.springframework.stereotype.Service;

@Data
public class LoginRequest {

    private String email;
    private String password;
}
