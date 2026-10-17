package com.hostelportal.hostelportalbackend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cleaning_records")
public class CleaningRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String area;

    @Column(nullable = false)
    private LocalDate cleaningDate;

    @Column(nullable = false)
    private String status;

    @Column(columnDefinition = "TEXT")
    private String remarks;

    public CleaningRecord() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public LocalDate getCleaningDate() {
        return cleaningDate;
    }

    public void setCleaningDate(LocalDate cleaningDate) {
        this.cleaningDate = cleaningDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}