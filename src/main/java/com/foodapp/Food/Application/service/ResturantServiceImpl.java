package com.foodapp.Food.Application.service;

import com.foodapp.Food.Application.dto.Resturantdto;
import com.foodapp.Food.Application.model.Address;
import com.foodapp.Food.Application.model.Resturant;
import com.foodapp.Food.Application.model.User;
import com.foodapp.Food.Application.repository.AddressRepository;
import com.foodapp.Food.Application.repository.ResturantRepository;
import com.foodapp.Food.Application.repository.UserRepository;
import com.foodapp.Food.Application.request.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ResturantServiceImpl implements ResturantService{

    @Autowired
    private ResturantRepository resturantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Resturant createResturant(CreateRestaurantRequest req, User user) {
        Address address = addressRepository.save(req.getAddress());
        Resturant resturant  = new Resturant();
        resturant.setAddress(req.getAddress());
        resturant.setContactInformation(req.getContactInformation());
        resturant.setCuisingtype(req.getCuisineType());
        resturant.setDescription(req.getDesrcription());
        resturant.setImages(req.getImage());
        resturant.setName(req.getName());
        resturant.setOpeninghour(req.getOpeningHours());
        resturant.setRegistrationdate(LocalDateTime.now());
        resturant.setOwner(user);

        return resturantRepository.save(resturant);
    }

    @Override
    public Resturant updateResturant(Long resturantsId, CreateRestaurantRequest updatedResturant) throws Exception {
        Resturant resturant = findResturantById(resturantsId);

        if(resturant.getCuisingtype()!=null){
            resturant.setCuisingtype(updatedResturant.getCuisineType());
        }

        if(resturant.getDescription()!=null){
            resturant.setDescription(updatedResturant.getDesrcription());
        }

        if(resturant.getName()!=null){
            resturant.setName(updatedResturant.getName());
        }

        return resturantRepository.save(resturant);
    }

    @Override
    public void deleteResturant(Long resturantId) throws Exception {
        Resturant resturant = findResturantById(resturantId);
        resturantRepository.deleteById(resturant.getResturantId());
    }

    @Override
    public List<Resturant> searchResturant(String keyword) {
        return resturantRepository.findBySearchQuery(keyword);
    }

    @Override
    public List<Resturant> getAllResturant() {
        return resturantRepository.findAll();
    }

    @Override
    public Resturant findResturantById(Long id) throws Exception {
        Optional<Resturant> opt = resturantRepository.findById(id);

        if(opt.isEmpty()){
            throw new Exception("restaurant not found with id" + id);
        }
        return opt.get();
    }

    @Override
    public Resturant getResturantByUserId(Long userId) throws Exception{
        Resturant resturant = resturantRepository.findByOwnerId(userId);
        if(resturant==null){
            throw  new Exception("resturant not found with owner id");
        }
        return resturant;
    }

    @Override
    public Resturantdto addtoFavouites(Long restaurantId, User user) throws Exception {
        Resturant resturant = findResturantById(restaurantId);

        Resturantdto dto = new Resturantdto();
        dto.setDescription(resturant.getDescription());
        dto.setImage(resturant.getImages());
        dto.setTitle(resturant.getName());
        dto.setResturantId(restaurantId);

        if(user.getFav().contains(dto)){
            user.getFav().remove(dto);
        }
        else{
            user.getFav().add(dto);
        }

        userRepository.save(user);
        return dto;
    }

    @Override
    public Resturant updateResturantStatus(Long id) throws Exception{
        Resturant resturant = findResturantById(id);
        resturant.setOpen(!resturant.isOpen());
        return resturantRepository.save(resturant);
    }
}
