package com.jobtracker.job.dto.request;

import com.jobtracker.job.enums.ApplicationStatus;


import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

public class UpdateJobRequest {

    @Size(max = 255)
    private String companyName;

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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<ContactRequest> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactRequest> contacts) {
        this.contacts = contacts;
    }

    @Size(max = 255)
    private String jobTitle;

    @Size(max = 255)
    private String location;

    @Size(max = 100)
    private String jobType;

    private ApplicationStatus applicationStatus;

    @Size(max = 2000)
    private String notes;

    @Valid
    private List<ContactRequest> contacts;
}
