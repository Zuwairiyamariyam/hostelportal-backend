package com.hostelportal.hostelportalbackend.controller;

import com.hostelportal.hostelportalbackend.dto.AdminLoginRequestDto;
import com.hostelportal.hostelportalbackend.dto.DashboardStatsDto;
import com.hostelportal.hostelportalbackend.entity.Admin;
import com.hostelportal.hostelportalbackend.repository.AdminRepository;
import com.hostelportal.hostelportalbackend.repository.ComplaintRepository;
import com.hostelportal.hostelportalbackend.repository.RoomRepository;
import com.hostelportal.hostelportalbackend.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final AdminRepository adminRepository;
    private final StudentRepository studentRepository;
    private final RoomRepository roomRepository;
    private final ComplaintRepository complaintRepository;

    public AdminController(AdminRepository adminRepository,
                           StudentRepository studentRepository,
                           RoomRepository roomRepository,
                           ComplaintRepository complaintRepository) {
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
        this.roomRepository = roomRepository;
        this.complaintRepository = complaintRepository;
    }

    // Admin Login API
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminLoginRequestDto loginDto) {
        Optional<Admin> adminOpt = adminRepository.findByAdminId(loginDto.getAdminId());

        if (adminOpt.isPresent() && adminOpt.get().getPassword().equals(loginDto.getPassword())) {
            Admin admin = adminOpt.get();
            admin.setPassword(null); // password security-kaga null panniduvom
            return ResponseEntity.ok(admin);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Admin ID or Password!");
    }

    // Admin Dashboard Card Counts API
    @GetMapping("/dashboard-stats")
    public ResponseEntity<DashboardStatsDto> getDashboardStats() {
        long totalStudents = studentRepository.count();
        long totalRooms = roomRepository.count();

        long pending = complaintRepository.findAll().stream()
                .filter(c -> "SUBMITTED".equalsIgnoreCase(c.getStatus())
                        || "UNDER_REVIEW".equalsIgnoreCase(c.getStatus())
                        || "ACKNOWLEDGED".equalsIgnoreCase(c.getStatus()))
                .count();

        long resolved = complaintRepository.findAll().stream()
                .filter(c -> "RESOLVED".equalsIgnoreCase(c.getStatus()))
                .count();

        long urgent = complaintRepository.findAll().stream()
                .filter(c -> "URGENT".equalsIgnoreCase(c.getPriority())
                        || "EMERGENCY".equalsIgnoreCase(c.getPriority()))
                .count();

        DashboardStatsDto stats = new DashboardStatsDto(totalStudents, totalRooms, pending, resolved, urgent);
        return ResponseEntity.ok(stats);
    }
}