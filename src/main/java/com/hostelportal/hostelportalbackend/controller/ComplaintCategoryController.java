package com.hostelportal.hostelportalbackend.controller;

import com.hostelportal.hostelportalbackend.entity.ComplaintCategory;
import com.hostelportal.hostelportalbackend.service.ComplaintCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class ComplaintCategoryController {

    private final ComplaintCategoryService complaintCategoryService;

    public ComplaintCategoryController(ComplaintCategoryService complaintCategoryService) {
        this.complaintCategoryService = complaintCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<ComplaintCategory>> getAllCategories() {
        return ResponseEntity.ok(complaintCategoryService.getAllCategories());
    }

    @PostMapping
    public ResponseEntity<ComplaintCategory> createCategory(@RequestBody ComplaintCategory category) {
        return ResponseEntity.ok(complaintCategoryService.saveCategory(category));
    }
}