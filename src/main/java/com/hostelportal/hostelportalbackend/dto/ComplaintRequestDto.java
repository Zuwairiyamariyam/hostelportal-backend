package com.hostelportal.hostelportalbackend.dto;

public class ComplaintRequestDto {
    private Long studentId;
    private Long categoryId;
    private String roomNo;
    private String description;
    private String priority;   // NORMAL, URGENT, EMERGENCY
    private Boolean anonymous;

    public ComplaintRequestDto() {}

    // Getters and Setters
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public String getRoomNo() { return roomNo; }
    public void setRoomNo(String roomNo) { this.roomNo = roomNo; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public Boolean getAnonymous() { return anonymous; }
    public void setAnonymous(Boolean anonymous) { this.anonymous = anonymous; }
}