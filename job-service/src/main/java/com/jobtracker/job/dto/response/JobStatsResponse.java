package com.jobtracker.job.dto.response;

import com.jobtracker.job.enums.ApplicationStatus;

import java.util.Map;

public class JobStatsResponse {

    private long totalJobs;
    private Map<ApplicationStatus, Long> byStatus;

    public JobStatsResponse(long totalJobs, Map<ApplicationStatus, Long> byStatus) {
        this.totalJobs = totalJobs;
        this.byStatus = byStatus;
    }

    public long getTotalJobs() {
        return totalJobs;
    }

    public Map<ApplicationStatus, Long> getByStatus() {
        return byStatus;
    }
}
