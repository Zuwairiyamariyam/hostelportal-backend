package com.hostelportal.hostelportalbackend.dto;

public class DashboardStatsDto {
    private long totalStudents;
    private long totalRooms;
    private long pendingComplaints;
    private long resolvedComplaints;
    private long urgentComplaints;

    public DashboardStatsDto() {}

    public DashboardStatsDto(long totalStudents, long totalRooms, long pendingComplaints, long resolvedComplaints, long urgentComplaints) {
        this.totalStudents = totalStudents;
        this.totalRooms = totalRooms;
        this.pendingComplaints = pendingComplaints;
        this.resolvedComplaints = resolvedComplaints;
        this.urgentComplaints = urgentComplaints;
    }

    public long getTotalStudents() { return totalStudents; }
    public void setTotalStudents(long totalStudents) { this.totalStudents = totalStudents; }

    public long getTotalRooms() { return totalRooms; }
    public void setTotalRooms(long totalRooms) { this.totalRooms = totalRooms; }

    public long getPendingComplaints() { return pendingComplaints; }
    public void setPendingComplaints(long pendingComplaints) { this.pendingComplaints = pendingComplaints; }

    public long getResolvedComplaints() { return resolvedComplaints; }
    public void setResolvedComplaints(long resolvedComplaints) { this.resolvedComplaints = resolvedComplaints; }

    public long getUrgentComplaints() { return urgentComplaints; }
    public void setUrgentComplaints(long urgentComplaints) { this.urgentComplaints = urgentComplaints; }
}