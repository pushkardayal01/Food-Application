package com.foodapp.Food.Application.controller;


import com.foodapp.Food.Application.config.JwtProvider;
import com.foodapp.Food.Application.model.Cart;
import com.foodapp.Food.Application.model.USER_ROLE;
import com.foodapp.Food.Application.model.User;
import com.foodapp.Food.Application.repository.CartRepository;
import com.foodapp.Food.Application.repository.UserRepository;
import com.foodapp.Food.Application.request.LoginRequest;
import com.foodapp.Food.Application.response.AuthResponse;
import com.foodapp.Food.Application.service.CustomerUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomerUserDetailService customerUserDetailService;

    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {
        User isEmailExits = userRepository.findByEmail(user.getEmail());

        if(isEmailExits!=null){
            throw new Exception("Email is already used with another account");
        }

        User createdUser = new User();
        createdUser.setEmail(user.getEmail());
        createdUser.setName(user.getName());
        createdUser.setRole(user.getRole());
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
        User saveUser = userRepository.save(createdUser);

        Cart cart = new Cart();
        cart.setCustomer(saveUser);
        cartRepository.save(cart);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registration sucess");
        authResponse.setRole(saveUser.getRole());

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);


    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest request){
        String username = request.getEmail();
        String password = request.getPassword();

        Authentication authentication = authenticate(username,password);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role =authorities.isEmpty()?null : authorities.iterator().next().getAuthority();
        String jwt = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registration sucess");
        authResponse.setRole(USER_ROLE.valueOf(role));

        return new ResponseEntity<>(authResponse, HttpStatus.OK);

    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customerUserDetailService.loadUserByUsername(username);
        if(userDetails ==null){
            throw new BadCredentialsException("Invalid Username");
        }

        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//        return new org.springframework.security.core.userdetails.User(userDetails.getUsername(),null,userDetails.getAuthorities());
    }
}
