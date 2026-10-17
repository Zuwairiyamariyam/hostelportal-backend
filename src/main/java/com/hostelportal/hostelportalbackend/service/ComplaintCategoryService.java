package com.hostelportal.hostelportalbackend.service;

import com.hostelportal.hostelportalbackend.entity.ComplaintCategory;
import com.hostelportal.hostelportalbackend.repository.ComplaintCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintCategoryService {

    private final ComplaintCategoryRepository complaintCategoryRepository;

    public ComplaintCategoryService(ComplaintCategoryRepository complaintCategoryRepository) {
        this.complaintCategoryRepository = complaintCategoryRepository;
    }

    public List<ComplaintCategory> getAllCategories() {
        return complaintCategoryRepository.findAll();
    }

    public ComplaintCategory getCategoryById(Long id) {
        return complaintCategoryRepository.findById(id).orElse(null);
    }

    public ComplaintCategory saveCategory(ComplaintCategory category) {
        return complaintCategoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        complaintCategoryRepository.deleteById(id);
    }
}
