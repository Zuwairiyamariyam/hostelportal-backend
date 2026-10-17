package com.hostelportal.hostelportalbackend.controller;

import com.hostelportal.hostelportalbackend.dto.StudentLoginRequestDto;
import com.hostelportal.hostelportalbackend.entity.Student;
import com.hostelportal.hostelportalbackend.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Check if student exists and return name for pre-login display
    @GetMapping("/verify/{rollNo}")
    public ResponseEntity<?> verifyStudent(@PathVariable String rollNo) {
        return studentRepository.findAll().stream()
                .filter(s -> s.getRollNo().equalsIgnoreCase(rollNo.trim()))
                .findFirst()
                .map(s -> ResponseEntity.ok(s.getName()))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found!"));
    }

    // Student Login verification (RollNo + Date of Birth)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody StudentLoginRequestDto loginDto) {
        Optional<Student> studentOpt = studentRepository.findByRollNoAndDob(
                loginDto.getRollNo().trim(), loginDto.getDob().trim());

        if (studentOpt.isPresent()) {
            return ResponseEntity.ok(studentOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid Register Number or Date of Birth!");
        }
    }

    // Get Roommates details based on roomNo (excluding current student)
    @GetMapping("/{id}/roommates")
    public ResponseEntity<List<Student>> getRoommates(@PathVariable Long id) {
        Optional<Student> studentOpt = studentRepository.findById(id);
        if (studentOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Student currentStudent = studentOpt.get();
        List<Student> roommates = studentRepository.findByRoomNo(currentStudent.getRoomNo())
                .stream()
                .filter(s -> !s.getId().equals(currentStudent.getId()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(roommates);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentRepository.findAll());
    }
}