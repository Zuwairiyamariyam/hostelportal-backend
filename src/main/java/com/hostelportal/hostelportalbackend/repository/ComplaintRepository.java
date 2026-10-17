package com.hostelportal.hostelportalbackend.repository;

import com.hostelportal.hostelportalbackend.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByStudentIdOrderByCreatedAtDesc(Long studentId);
    List<Complaint> findByStatus(String status);
    List<Complaint> findAllByOrderByCreatedAtDesc();
}