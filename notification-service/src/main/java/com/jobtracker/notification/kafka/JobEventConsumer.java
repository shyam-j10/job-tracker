package com.jobtracker.notification.kafka;

import com.jobtracker.notification.kafka.event.JobEvent;
import com.jobtracker.notification.service.EmailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class JobEventConsumer {

    private final EmailService emailService;
    private static final Logger log = LoggerFactory.getLogger(JobEventConsumer.class);


    public JobEventConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(
        topics = "job-events",
        groupId = "notification-group"
    )
    public void consume(JobEvent event) {
        try{
            String subject = "Job Update: " + event.getEventType();
            
            String body = """
                Job ID: %s
                Event: %s
                Description: %s
                Time: %s
                """.formatted(
                    event.getJobId(),
                    event.getEventType(),
                    event.getDescription(),
                    event.getTimestamp()
            );
                    
            emailService.sendJobNotification(
                event.getUserEmail(),
                subject,
                body
            );
                        
            System.out.println("📧 Email sent for event: " + event.getEventType());
        }catch(Exception e) {
            log.error("Couldn't send email,skipping retry", e);
        }
    }
}
