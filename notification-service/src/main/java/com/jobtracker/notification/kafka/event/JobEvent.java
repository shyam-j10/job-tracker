package com.jobtracker.notification.kafka.event;

import java.time.LocalDateTime;

public class JobEvent {

    private Long jobId;
    private Long userId;
    private String eventType;
    private String userEmail;
    private String description;
    private LocalDateTime timestamp;

    public JobEvent() {
        // REQUIRED for Kafka deserialization
    }

    public Long getJobId() {
        return jobId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
