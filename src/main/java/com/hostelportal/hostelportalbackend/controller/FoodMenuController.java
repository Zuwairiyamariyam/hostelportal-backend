package com.hostelportal.hostelportalbackend.controller;

import com.hostelportal.hostelportalbackend.entity.FoodMenu;
import com.hostelportal.hostelportalbackend.repository.FoodMenuRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@CrossOrigin(origins = "*")
public class FoodMenuController {

    private final FoodMenuRepository foodMenuRepository;

    public FoodMenuController(FoodMenuRepository foodMenuRepository) {
        this.foodMenuRepository = foodMenuRepository;
    }

    // View all food menu / today's menu
    @GetMapping
    public ResponseEntity<List<FoodMenu>> getAllFoodMenu() {
        return ResponseEntity.ok(foodMenuRepository.findAll());
    }

    // Admin adding menu
    @PostMapping
    public ResponseEntity<FoodMenu> createMenu(@RequestBody FoodMenu foodMenu) {
        return ResponseEntity.ok(foodMenuRepository.save(foodMenu));
    }
}