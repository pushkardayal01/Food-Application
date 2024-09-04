package com.foodapp.Food.Application.service;

import com.foodapp.Food.Application.dto.Resturantdto;
import com.foodapp.Food.Application.model.Resturant;
import com.foodapp.Food.Application.model.User;
import com.foodapp.Food.Application.request.CreateRestaurantRequest;

import java.util.List;

public interface ResturantService {

    public Resturant createResturant(CreateRestaurantRequest req, User user);

    public Resturant updateResturant(Long resturantsId,CreateRestaurantRequest updatedResturant) throws Exception;

    public void deleteResturant(Long resturantId) throws Exception;

    public List<Resturant> searchResturant(String keyword);

    public List<Resturant> getAllResturant();

    public Resturant findResturantById(Long id) throws Exception;

    public Resturant getResturantByUserId(Long userId) throws Exception;


    public Resturantdto addtoFavouites(Long restaurantId,User user) throws Exception;


    public Resturant updateResturantStatus(Long id) throws Exception;


}
