package com.jobtracker.job.repository;

import com.jobtracker.job.entity.JobEntry;
import com.jobtracker.job.enums.ApplicationStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobEntryRepository extends JpaRepository<JobEntry, Long> {


    // Get a specific job owned by a user
    Optional<JobEntry> findByIdAndUserId(Long id, Long userId);

    // Search jobs with filters
    @Query("""
        SELECT j FROM JobEntry j
        WHERE j.userId = :userId
          AND (:status IS NULL OR j.applicationStatus = :status)
          AND (:company IS NULL OR LOWER(j.companyName) LIKE LOWER(CONCAT('%', :company, '%')))
          AND (:jobType IS NULL OR j.jobType = :jobType)
          AND (
                :q IS NULL OR
                LOWER(j.jobTitle) LIKE LOWER(CONCAT('%', :q, '%')) OR
                LOWER(j.companyName) LIKE LOWER(CONCAT('%', :q, '%'))
          )
    """)
    List<JobEntry> searchJobs(
            @Param("userId") Long userId,
            @Param("status") ApplicationStatus status,
            @Param("company") String company,
            @Param("jobType") String jobType,
            @Param("q") String q
    );

    // Get Dashboard Stats
    @Query("""
        SELECT j.applicationStatus, COUNT(j)
        FROM JobEntry j
        WHERE j.userId = :userId
        GROUP BY j.applicationStatus
    """)
    List<Object[]> countByStatus(@Param("userId") Long userId);
}
