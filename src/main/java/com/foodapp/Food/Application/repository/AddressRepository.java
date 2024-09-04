package com.foodapp.Food.Application.repository;

import com.foodapp.Food.Application.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
