package com.jobtracker.job.entity;

import com.jobtracker.job.enums.ContactRole;
import jakarta.persistence.*;

@Entity
@Table(name = "job_contacts")
public class Contact {

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

    public JobEntry getJobEntry() {
        return jobEntry;
    }

    public void setJobEntry(JobEntry jobEntry) {
        this.jobEntry = jobEntry;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ContactRole role;

    private String email;
    private String phone;

    @Column(length = 1000)
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private JobEntry jobEntry;

    // getters & setters
}
