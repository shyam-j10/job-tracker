package com.jobtracker.job.dto.response;

import com.jobtracker.job.enums.ApplicationStatus;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class JobResponse {

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
