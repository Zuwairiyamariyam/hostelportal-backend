package com.hostelportal.hostelportalbackend.dto;

public class AdminLoginRequestDto {
    private String adminId;
    private String password;

    public AdminLoginRequestDto() {}

    public String getAdminId() { return adminId; }
    public void setAdminId(String adminId) { this.adminId = adminId; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}