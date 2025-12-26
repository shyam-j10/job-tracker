package com.jobtracker.job.dto.request;

import com.jobtracker.job.enums.ContactRole;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
public class ContactRequest {

    @NotBlank(message = "Contact name is required")
    private String name;

    @NotNull(message = "Contact role is required")
    private ContactRole role;

    @Email(message = "Invalid email format")
    private String email;

    @Size(max = 20)
    private String phone;

    @Size(max = 1000)
    private String notes;
}
