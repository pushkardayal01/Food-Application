package com.foodapp.Food.Application.repository;

import com.foodapp.Food.Application.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
