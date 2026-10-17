package com.hostelportal.hostelportalbackend.dto;

public class StatusUpdateDto {
    private String status;     // ACKNOWLEDGED, UNDER_REVIEW, ACTION_TAKEN, RESOLVED, REJECTED
    private String message;    // Admin comment/remark
    private String updatedBy;  // Admin name or ID

    public StatusUpdateDto() {}

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(String updatedBy) { this.updatedBy = updatedBy; }
}
