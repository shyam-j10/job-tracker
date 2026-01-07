package com.jobtracker.job.controller;

import com.jobtracker.job.dto.request.CreateJobRequest;
import com.jobtracker.job.dto.request.UpdateJobRequest;
import com.jobtracker.job.dto.response.JobResponse;
import com.jobtracker.job.dto.response.JobStatsResponse;
import com.jobtracker.job.enums.ApplicationStatus;
import com.jobtracker.job.service.JobService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // CREATE JOB
    @PostMapping
    public ResponseEntity<JobResponse> createJob(
            @Valid @RequestBody CreateJobRequest request,
            @RequestHeader("X-User-Id") Long userId,
            @RequestHeader("X-User-Email") String email
    ) {
        JobResponse response = jobService.createJob(request, userId,email);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //GET JOB BY FILTERS
    @GetMapping
    public List<JobResponse> getJobs(
            @RequestHeader("X-User-Id") Long userId,
            @RequestParam(required = false) ApplicationStatus status,
            @RequestParam(required = false) String company,
            @RequestParam(required = false) String jobType,
            @RequestParam(required = false) String q
    ) {
        return jobService.searchJobs(userId, status, company, jobType, q);
    }

    // GET JOB BY ID
    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getJobById(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long userId
    ) {
        return ResponseEntity.ok(jobService.getJobById(id, userId));
    }

    // GET DASHBOARD STATS
    @GetMapping("/stats")
    public JobStatsResponse getStats(
            @RequestHeader("X-User-Id") Long userId
    ) {
        return jobService.getStats(userId);
    }

    // UPDATE JOB
    @PutMapping("/{id}")
    public ResponseEntity<JobResponse> updateJob(
            @PathVariable Long id,
            @Valid @RequestBody UpdateJobRequest request,
            @RequestHeader("X-User-Id") Long userId,
            @RequestHeader("X-User-Email") String email
    ) {
        return ResponseEntity.ok(jobService.updateJob(id, request, userId, email));
    }

    // DELETE JOB
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long userId,
            @RequestHeader("X-User-Email") String email
    ) {
        jobService.deleteJob(id, userId, email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Job deleted successfully");
    }
}
