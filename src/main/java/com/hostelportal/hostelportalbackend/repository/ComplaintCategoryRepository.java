package com.hostelportal.hostelportalbackend.repository;

import com.hostelportal.hostelportalbackend.entity.ComplaintCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintCategoryRepository extends JpaRepository<ComplaintCategory, Long> {
}
