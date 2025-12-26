package com.jobtracker.job.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import com.jobtracker.job.enums.ApplicationStatus;

@Getter
@Setter
public class CreateJobRequest {

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Job title is required")
    private String jobTitle;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Job type is required")
    private String jobType;

    @NotNull(message = "Application status is required")
    private ApplicationStatus applicationStatus;

    @NotNull(message = "Applied date is required")
    private LocalDate appliedDate;

    @Size(max = 2000, message = "Notes cannot exceed 2000 characters")
    private String notes;

    @Valid
    private List<ContactRequest> contacts;
}

