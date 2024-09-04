package com.foodapp.Food.Application.service;

import com.foodapp.Food.Application.model.User;

public interface UserService {

    public User findUserByJwtToken(String jwt) throws Exception;

    public User findUserByEmail(String email) throws Exception;


}
