package com.hostelportal.hostelportalbackend.controller;

import com.hostelportal.hostelportalbackend.entity.Notification;
import com.hostelportal.hostelportalbackend.repository.NotificationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    private final NotificationRepository notificationRepository;

    public NotificationController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    // Specific student oda notification messages
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Notification>> getStudentNotifications(@PathVariable Long studentId) {
        return ResponseEntity.ok(notificationRepository.findAll()
                .stream()
                .filter(n -> n.getStudentId().equals(studentId))
                .sorted((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()))
                .toList());
    }

    // Mark single notification as read
    @PutMapping("/{id}/read")
    public ResponseEntity<?> markAsRead(@PathVariable Long id) {
        return notificationRepository.findById(id).map(notif -> {
            notif.setIsRead(true);
            notificationRepository.save(notif);
            return ResponseEntity.ok("Marked as read");
        }).orElse(ResponseEntity.notFound().build());
    }

    // Mark ALL notifications of a student as read (Fixed: stops count from reappearing on refresh)
    @PutMapping("/student/{studentId}/read")
    public ResponseEntity<?> markAllAsReadForStudent(@PathVariable Long studentId) {
        List<Notification> studentNotifs = notificationRepository.findAll()
                .stream()
                .filter(n -> n.getStudentId().equals(studentId) && Boolean.FALSE.equals(n.getIsRead()))
                .toList();

        for (Notification notif : studentNotifs) {
            notif.setIsRead(true);
            notificationRepository.save(notif);
        }

        return ResponseEntity.ok("All notifications marked as read");
    }
}