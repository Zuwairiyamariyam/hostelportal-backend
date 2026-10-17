package com.hostelportal.hostelportalbackend.repository;

import com.hostelportal.hostelportalbackend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByRollNoAndDob(String rollNo, String dob);
    Optional<Student> findByRollNo(String rollNo);
    boolean existsByRollNo(String rollNo);
    List<Student> findByRoomNo(String roomNo);
}