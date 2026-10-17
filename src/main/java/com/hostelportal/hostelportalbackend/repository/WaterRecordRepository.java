package com.hostelportal.hostelportalbackend.repository;

import com.hostelportal.hostelportalbackend.entity.WaterRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaterRecordRepository extends JpaRepository<WaterRecord, Long> {
}
