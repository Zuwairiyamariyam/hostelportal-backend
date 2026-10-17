package com.hostelportal.hostelportalbackend.service;

import com.hostelportal.hostelportalbackend.entity.ComplaintUpdate;
import com.hostelportal.hostelportalbackend.repository.ComplaintUpdateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintUpdateService {

    private final ComplaintUpdateRepository complaintUpdateRepository;

    public ComplaintUpdateService(ComplaintUpdateRepository complaintUpdateRepository) {
        this.complaintUpdateRepository = complaintUpdateRepository;
    }

    public List<ComplaintUpdate> getAllUpdates() {
        return complaintUpdateRepository.findAll();
    }

    public ComplaintUpdate getUpdateById(Long id) {
        return complaintUpdateRepository.findById(id).orElse(null);
    }

    public ComplaintUpdate saveUpdate(ComplaintUpdate complaintUpdate) {
        return complaintUpdateRepository.save(complaintUpdate);
    }

    public void deleteUpdate(Long id) {
        complaintUpdateRepository.deleteById(id);
    }
}
