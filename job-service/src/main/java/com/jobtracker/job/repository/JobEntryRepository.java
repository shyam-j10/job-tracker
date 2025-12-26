package com.jobtracker.job.repository;

import com.jobtracker.job.entity.JobEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobEntryRepository extends JpaRepository<JobEntry, Long> {

    // Get all jobs for a user
    List<JobEntry> findByUserId(Long userId);

    // Get a specific job owned by a user
    Optional<JobEntry> findByIdAndUserId(Long id, Long userId);
}
