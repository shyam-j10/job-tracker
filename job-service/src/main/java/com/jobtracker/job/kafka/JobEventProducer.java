package com.jobtracker.job.kafka;

import com.jobtracker.job.kafka.event.JobEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobEventProducer {

    private final KafkaTemplate<String, JobEvent> kafkaTemplate;
    private static final String TOPIC = "job-events";
    private static final Logger log = LoggerFactory.getLogger(JobEventProducer.class);


    public JobEventProducer(KafkaTemplate<String, JobEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(JobEvent event) {
        log.info("Publishing event to Kafka: {}", event.getEventType());
        kafkaTemplate.send(TOPIC, event);
    }
}
