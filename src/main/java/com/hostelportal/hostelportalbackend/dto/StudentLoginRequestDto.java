package com.hostelportal.hostelportalbackend.dto;

public class StudentLoginRequestDto {
    private String rollNo;
    private String dob;

    public StudentLoginRequestDto() {}

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}