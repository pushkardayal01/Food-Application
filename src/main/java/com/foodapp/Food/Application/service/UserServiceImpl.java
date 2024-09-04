package com.foodapp.Food.Application.service;

import com.foodapp.Food.Application.config.JwtProvider;
import com.foodapp.Food.Application.model.User;
import com.foodapp.Food.Application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements  UserService{

    @Autowired
    private UserRepository userrepo;

    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        try {
            String email = jwtProvider.getEmailFromJwtToken(jwt);
            return this.findUserByEmail(email);
        } catch (Exception e) {
            throw new Exception("User not found");
        }
    }
    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userrepo.findByEmail(email);
        if(user==null){
            throw new Exception("User not found");
        }

        return user;
    }

}
