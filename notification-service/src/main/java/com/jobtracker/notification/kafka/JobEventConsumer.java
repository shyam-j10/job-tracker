package com.jobtracker.notification.kafka;

import com.jobtracker.notification.kafka.event.JobEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class JobEventConsumer {

    @KafkaListener(
        topics = "job-events",
        groupId = "notification-group"
    )
    public void consume(JobEvent event) {

        System.out.println("🔔 Notification received");
        System.out.println("User ID: " + event.getUserId());
        System.out.println("Job ID: " + event.getJobId());
        System.out.println("Event Type: " + event.getEventType());
        System.out.println("Description: " + event.getDescription());
        System.out.println("Time: " + event.getTimestamp());
        System.out.println("--------------------------------");
    }
}
