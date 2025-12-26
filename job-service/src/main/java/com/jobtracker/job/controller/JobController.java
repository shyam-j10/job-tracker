package com.jobtracker.job.controller;

import com.jobtracker.job.dto.request.CreateJobRequest;
import com.jobtracker.job.dto.request.UpdateJobRequest;
import com.jobtracker.job.dto.response.JobResponse;
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
            @RequestHeader("X-User-Id") Long userId
    ) {
        JobResponse response = jobService.createJob(request, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // GET ALL JOBS
    @GetMapping
    public ResponseEntity<List<JobResponse>> getAllJobs(
            @RequestHeader("X-User-Id") Long userId
    ) {
        return ResponseEntity.ok(jobService.getAllJobs(userId));
    }

    // GET JOB BY ID
    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getJobById(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long userId
    ) {
        return ResponseEntity.ok(jobService.getJobById(id, userId));
    }

    // UPDATE JOB
    @PutMapping("/{id}")
    public ResponseEntity<JobResponse> updateJob(
            @PathVariable Long id,
            @Valid @RequestBody UpdateJobRequest request,
            @RequestHeader("X-User-Id") Long userId
    ) {
        return ResponseEntity.ok(jobService.updateJob(id, request, userId));
    }

    // DELETE JOB
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long userId
    ) {
        jobService.deleteJob(id, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Job deleted successfully");
    }
}
