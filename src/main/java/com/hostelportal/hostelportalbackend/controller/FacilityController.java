package com.hostelportal.hostelportalbackend.controller;

import com.hostelportal.hostelportalbackend.entity.CleaningRecord;
import com.hostelportal.hostelportalbackend.entity.WaterRecord;
import com.hostelportal.hostelportalbackend.repository.CleaningRecordRepository;
import com.hostelportal.hostelportalbackend.repository.WaterRecordRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facilities")
@CrossOrigin(origins = "*")
public class FacilityController {

    private final CleaningRecordRepository cleaningRecordRepository;
    private final WaterRecordRepository waterRecordRepository;

    public FacilityController(CleaningRecordRepository cleaningRecordRepository,
                              WaterRecordRepository waterRecordRepository) {
        this.cleaningRecordRepository = cleaningRecordRepository;
        this.waterRecordRepository = waterRecordRepository;
    }

    // Cleaning records
    @GetMapping("/cleaning")
    public ResponseEntity<List<CleaningRecord>> getCleaningRecords() {
        return ResponseEntity.ok(cleaningRecordRepository.findAll());
    }

    @PostMapping("/cleaning")
    public ResponseEntity<CleaningRecord> addCleaningRecord(@RequestBody CleaningRecord record) {
        return ResponseEntity.ok(cleaningRecordRepository.save(record));
    }

    // Water records
    @GetMapping("/water")
    public ResponseEntity<List<WaterRecord>> getWaterRecords() {
        return ResponseEntity.ok(waterRecordRepository.findAll());
    }

    @PostMapping("/water")
    public ResponseEntity<WaterRecord> addWaterRecord(@RequestBody WaterRecord record) {
        return ResponseEntity.ok(waterRecordRepository.save(record));
    }
}