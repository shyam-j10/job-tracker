package com.jobtracker.job.dto.request;

import com.jobtracker.job.enums.ApplicationStatus;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

@Getter
@Setter
public class UpdateJobRequest {

    @Size(max = 255)
    private String companyName;

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
