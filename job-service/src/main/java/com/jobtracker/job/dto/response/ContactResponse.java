package com.jobtracker.job.dto.response;

import com.jobtracker.job.enums.ContactRole;


public class ContactResponse {

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
    private Long id;
    private String name;
    private ContactRole role;
    private String email;
    private String phone;
    private String notes;

    // getters & setters
}
