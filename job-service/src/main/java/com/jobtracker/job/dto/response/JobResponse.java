package com.jobtracker.job.dto.response;

import com.jobtracker.job.enums.ApplicationStatus;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class JobResponse {

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getJobType() {
        return jobType;
    }
    public void setJobType(String jobType) {
        this.jobType = jobType;
    }
    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }
    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
    public LocalDate getAppliedDate() {
        return appliedDate;
    }
    public void setAppliedDate(LocalDate appliedDate) {
        this.appliedDate = appliedDate;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public List<ContactResponse> getContacts() {
        return contacts;
    }
    public void setContacts(List<ContactResponse> contacts) {
        this.contacts = contacts;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    private Long id;

    private String companyName;
    private String jobTitle;
    private String location;
    private String jobType;

    private ApplicationStatus applicationStatus;
    private LocalDate appliedDate;
    private String notes;

    private List<ContactResponse> contacts;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // getters & setters
}
