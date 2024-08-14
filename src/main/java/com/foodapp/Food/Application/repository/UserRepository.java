package com.foodapp.Food.Application.repository;

import com.foodapp.Food.Application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
