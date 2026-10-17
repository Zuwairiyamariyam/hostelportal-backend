package com.hostelportal.hostelportalbackend.repository;

import com.hostelportal.hostelportalbackend.entity.FoodMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodMenuRepository extends JpaRepository<FoodMenu, Long> {
}
