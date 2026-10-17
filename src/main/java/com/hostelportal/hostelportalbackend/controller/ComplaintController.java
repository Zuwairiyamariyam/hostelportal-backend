package com.hostelportal.hostelportalbackend.controller;

import com.hostelportal.hostelportalbackend.dto.ComplaintRequestDto;
import com.hostelportal.hostelportalbackend.dto.StatusUpdateDto;
import com.hostelportal.hostelportalbackend.entity.Complaint;
import com.hostelportal.hostelportalbackend.entity.ComplaintCategory;
import com.hostelportal.hostelportalbackend.entity.Notification;
import com.hostelportal.hostelportalbackend.repository.ComplaintCategoryRepository;
import com.hostelportal.hostelportalbackend.repository.ComplaintRepository;
import com.hostelportal.hostelportalbackend.repository.NotificationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/complaints")
@CrossOrigin(origins = "*")
public class ComplaintController {

    private final ComplaintRepository complaintRepository;
    private final NotificationRepository notificationRepository;
    private final ComplaintCategoryRepository complaintCategoryRepository;

    public ComplaintController(ComplaintRepository complaintRepository,
                               NotificationRepository notificationRepository,
                               ComplaintCategoryRepository complaintCategoryRepository) {
        this.complaintRepository = complaintRepository;
        this.notificationRepository = notificationRepository;
        this.complaintCategoryRepository = complaintCategoryRepository;
    }

    @GetMapping
    public ResponseEntity<List<Complaint>> getAllComplaints() {
        return ResponseEntity.ok(complaintRepository.findAllByOrderByCreatedAtDesc());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Complaint>> getComplaintsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(complaintRepository.findByStudentIdOrderByCreatedAtDesc(studentId));
    }

    @PostMapping
    public ResponseEntity<Complaint> createComplaint(@RequestBody ComplaintRequestDto dto) {
        Complaint complaint = new Complaint();
        complaint.setComplaintNumber("CMP-" + UUID.randomUUID().toString().substring(0, 7).toUpperCase());
        complaint.setStudentId(dto.getStudentId());
        complaint.setCategoryId(dto.getCategoryId());
        complaint.setRoomNo(dto.getRoomNo());
        complaint.setDescription(dto.getDescription());
        complaint.setPriority(dto.getPriority() != null ? dto.getPriority() : "NORMAL");
        complaint.setAnonymous(dto.getAnonymous() != null ? dto.getAnonymous() : false);
        complaint.setStatus("SUBMITTED");
        complaint.setCreatedAt(LocalDateTime.now());
        complaint.setUpdatedAt(LocalDateTime.now());

        Complaint savedComplaint = complaintRepository.save(complaint);
        return ResponseEntity.ok(savedComplaint);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody StatusUpdateDto statusDto) {
        return complaintRepository.findById(id).map(complaint -> {
            complaint.setStatus(statusDto.getStatus());
            complaint.setUpdatedAt(LocalDateTime.now());
            Complaint updated = complaintRepository.save(complaint);

            // Fetch category name
            String categoryName = complaintCategoryRepository.findById(complaint.getCategoryId())
                    .map(ComplaintCategory::getName)
                    .orElse("General");

            Notification notif = new Notification();
            notif.setStudentId(complaint.getStudentId());
            notif.setComplaintId(complaint.getId());

            String adminReply = (statusDto.getMessage() != null && !statusDto.getMessage().trim().isEmpty())
                    ? statusDto.getMessage().trim()
                    : "Status updated to " + statusDto.getStatus();

            // Format: #CMP-XXXX • [CATEGORY] • [STATUS]::Admin Reply
            String formattedMessage = "#" + complaint.getComplaintNumber() + " • " + categoryName + " • " + statusDto.getStatus() + "::" + adminReply;

            notif.setMessage(formattedMessage);
            notif.setIsRead(false);
            notif.setCreatedAt(LocalDateTime.now());
            notificationRepository.save(notif);

            return ResponseEntity.ok(updated);
        }).orElse(ResponseEntity.notFound().build());
    }
}