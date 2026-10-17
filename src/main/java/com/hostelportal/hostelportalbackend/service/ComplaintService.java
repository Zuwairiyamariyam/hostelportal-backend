package com.hostelportal.hostelportalbackend.service;

import com.hostelportal.hostelportalbackend.dto.ComplaintRequestDto;
import com.hostelportal.hostelportalbackend.dto.StatusUpdateDto;
import com.hostelportal.hostelportalbackend.entity.Complaint;
import com.hostelportal.hostelportalbackend.entity.ComplaintUpdate;
import com.hostelportal.hostelportalbackend.entity.Notification;
import com.hostelportal.hostelportalbackend.repository.ComplaintRepository;
import com.hostelportal.hostelportalbackend.repository.ComplaintUpdateRepository;
import com.hostelportal.hostelportalbackend.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final ComplaintUpdateRepository complaintUpdateRepository;
    private final NotificationRepository notificationRepository;

    public ComplaintService(ComplaintRepository complaintRepository,
                            ComplaintUpdateRepository complaintUpdateRepository,
                            NotificationRepository notificationRepository) {
        this.complaintRepository = complaintRepository;
        this.complaintUpdateRepository = complaintUpdateRepository;
        this.notificationRepository = notificationRepository;
    }

    // Student complaint raise panrathu
    @Transactional
    public Complaint raiseComplaint(ComplaintRequestDto dto) {
        Complaint complaint = new Complaint();

        // Custom unique complaint number: CMP-XXXXXX
        String compNo = "CMP-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        complaint.setComplaintNumber(compNo);
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

        // Initial complaint history update
        ComplaintUpdate update = new ComplaintUpdate();
        update.setComplaintId(savedComplaint.getId());
        update.setStatus("SUBMITTED");
        update.setMessage("Complaint raised successfully.");
        update.setUpdatedBy("STUDENT");
        update.setCreatedAt(LocalDateTime.now());
        complaintUpdateRepository.save(update);

        // Automatic Notification trigger for Student
        Notification notification = new Notification();
        notification.setStudentId(savedComplaint.getStudentId());
        notification.setComplaintId(savedComplaint.getId());
        notification.setMessage("Your complaint #" + compNo + " has been registered successfully.");
        notification.setIsRead(false);
        notification.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(notification);

        return savedComplaint;
    }

    // Admin status update panna automatic reply/notification pogum
    @Transactional
    public Complaint updateComplaintStatus(Long complaintId, StatusUpdateDto dto) {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found with id: " + complaintId));

        complaint.setStatus(dto.getStatus());
        complaint.setUpdatedAt(LocalDateTime.now());
        Complaint updatedComplaint = complaintRepository.save(complaint);

        // History track pannuvom
        ComplaintUpdate update = new ComplaintUpdate();
        update.setComplaintId(complaint.getId());
        update.setStatus(dto.getStatus());
        update.setMessage(dto.getMessage());
        update.setUpdatedBy(dto.getUpdatedBy() != null ? dto.getUpdatedBy() : "ADMIN");
        update.setCreatedAt(LocalDateTime.now());
        complaintUpdateRepository.save(update);

        // Student ku automatic update notification
        Notification notification = new Notification();
        notification.setStudentId(complaint.getStudentId());
        notification.setComplaintId(complaint.getId());
        notification.setMessage("Update on #" + complaint.getComplaintNumber() + ": Status changed to " + dto.getStatus() + ". Note: " + dto.getMessage());
        notification.setIsRead(false);
        notification.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(notification);

        return updatedComplaint;
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<Complaint> getComplaintsByStudent(Long studentId) {
        return complaintRepository.findByStudentIdOrderByCreatedAtDesc(studentId);
    }

    public Complaint getComplaintById(Long id) {
        return complaintRepository.findById(id).orElse(null);
    }
}