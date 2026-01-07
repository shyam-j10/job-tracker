package com.jobtracker.job.dto.request;

import com.jobtracker.job.enums.ContactRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ContactRequest {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContactRole getRole() {
        return role;
    }

    public void setRole(ContactRole role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

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
