package com.jobtracker.job.service;

import com.jobtracker.job.dto.request.CreateJobRequest;
import com.jobtracker.job.dto.request.UpdateJobRequest;
import com.jobtracker.job.dto.response.ContactResponse;
import com.jobtracker.job.dto.response.JobResponse;
import com.jobtracker.job.entity.Contact;
import com.jobtracker.job.entity.JobEntry;
import com.jobtracker.job.enums.ApplicationStatus;
import com.jobtracker.job.exception.ResourceNotFoundException;
import com.jobtracker.job.kafka.JobEventProducer;
import com.jobtracker.job.kafka.event.JobEvent;
import com.jobtracker.job.repository.JobEntryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class JobService {

    private final JobEntryRepository jobEntryRepository;
    private final JobEventProducer jobEventProducer;
    private static final Logger log = LoggerFactory.getLogger(JobService.class);


    public JobService(JobEntryRepository jobEntryRepository,
                    JobEventProducer jobEventProducer) {
        this.jobEntryRepository = jobEntryRepository;
        this.jobEventProducer = jobEventProducer;
    }

    private JobResponse mapToResponse(JobEntry job) {

        JobResponse response = new JobResponse();
        response.setId(job.getId());
        response.setCompanyName(job.getCompanyName());
        response.setJobTitle(job.getJobTitle());
        response.setLocation(job.getLocation());
        response.setJobType(job.getJobType());
        response.setApplicationStatus(job.getApplicationStatus());
        response.setAppliedDate(job.getAppliedDate());
        response.setNotes(job.getNotes());
        response.setCreatedAt(job.getCreatedAt());
        response.setUpdatedAt(job.getUpdatedAt());

        response.setContacts(
                job.getContacts().stream().map(c -> {
                    ContactResponse cr = new ContactResponse();
                    cr.setId(c.getId());
                    cr.setName(c.getName());
                    cr.setRole(c.getRole());
                    cr.setEmail(c.getEmail());
                    cr.setPhone(c.getPhone());
                    cr.setNotes(c.getNotes());
                    return cr;
                }).collect(Collectors.toList())
        );

        return response;
    }

    public JobResponse createJob(CreateJobRequest request, Long userId, String email) {

        JobEntry job = new JobEntry();
        job.setUserId(userId);
        job.setCompanyName(request.getCompanyName());
        job.setJobTitle(request.getJobTitle());
        job.setLocation(request.getLocation());
        job.setJobType(request.getJobType());
        job.setApplicationStatus(request.getApplicationStatus());
        job.setAppliedDate(request.getAppliedDate());
        job.setNotes(request.getNotes());

        if (request.getContacts() != null) {
            request.getContacts().forEach(c -> {
                Contact contact = new Contact();
                contact.setName(c.getName());
                contact.setRole(c.getRole());
                contact.setEmail(c.getEmail());
                contact.setPhone(c.getPhone());
                contact.setNotes(c.getNotes());

                contact.setJobEntry(job);
                job.getContacts().add(contact);
            });
        }

        JobEntry saved = jobEntryRepository.save(job);

        jobEventProducer.send(
            new JobEvent(
                saved.getId(),
                userId,
                "JOB_CREATED",
                email,
                "Job created for " + saved.getCompanyName() + " with role of " + saved.getJobTitle()
            )
        );


        return mapToResponse(saved);
    }

    public List<JobResponse> getAllJobs(Long userId) {
        return jobEntryRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public JobResponse getJobById(Long jobId, Long userId) {
        JobEntry job = jobEntryRepository
                .findByIdAndUserId(jobId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        return mapToResponse(job);
    }

    public JobResponse updateJob(Long jobId, UpdateJobRequest request, Long userId, String email) {

        JobEntry job = jobEntryRepository
        .findByIdAndUserId(jobId, userId)
        .orElseThrow(() -> new ResourceNotFoundException("Job not found"));
        
        log.info("Update job processing");
    
        ApplicationStatus oldStatus = job.getApplicationStatus();
        
        if (request.getCompanyName() != null) {
            job.setCompanyName(request.getCompanyName());
        }
        if (request.getJobTitle() != null) {
            job.setJobTitle(request.getJobTitle());
        }
        if (request.getLocation() != null) {
            job.setLocation(request.getLocation());
        }
        if (request.getJobType() != null) {
            job.setJobType(request.getJobType());
        }
        if (request.getApplicationStatus() != null) {
            job.setApplicationStatus(request.getApplicationStatus());
        }
        if (request.getNotes() != null) {
            job.setNotes(request.getNotes());
        }

        if (request.getApplicationStatus() != null &&
            !request.getApplicationStatus().equals(oldStatus)) {
            jobEventProducer.send(
                new JobEvent(
                    job.getId(),
                    userId,
                    "JOB_STATUS_UPDATED",
                    email,
                    "Status changed to " + job.getApplicationStatus() + "for" + job.getCompanyName() + " with role of " + job.getJobTitle()
                )
        );
        }

        // Replace contacts safely
        job.getContacts().clear();

        if (request.getContacts() != null) {
            request.getContacts().forEach(c -> {
                Contact contact = new Contact();
                contact.setName(c.getName());
                contact.setRole(c.getRole());
                contact.setEmail(c.getEmail());
                contact.setPhone(c.getPhone());
                contact.setNotes(c.getNotes());

                contact.setJobEntry(job);
                job.getContacts().add(contact);
            });
        }

        return mapToResponse(job);
    }

    public void deleteJob(Long jobId, Long userId, String email) {

        JobEntry job = jobEntryRepository
                .findByIdAndUserId(jobId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        jobEventProducer.send(
            new JobEvent(
                job.getId(),
                userId,
                "JOB_DELETED",
                email,
                "Job deleted for " + job.getCompanyName() + " with role of " + job.getJobTitle()
            )
        );


        jobEntryRepository.delete(job);
    }



}
