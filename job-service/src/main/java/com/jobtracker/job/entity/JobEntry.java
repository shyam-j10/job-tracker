package com.jobtracker.job.entity;

import com.jobtracker.job.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "job_entries")
@Getter
@Setter
public class JobEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // comes from gateway header
    @Column(nullable = false)
    private Long userId;

    private String companyName;
    private String jobTitle;
    private String location;
    private String jobType;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    private LocalDate appliedDate;

    @Column(length = 2000)
    private String notes;

    @OneToMany(
            mappedBy = "jobEntry",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Contact> contacts = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // getters & setters

}
