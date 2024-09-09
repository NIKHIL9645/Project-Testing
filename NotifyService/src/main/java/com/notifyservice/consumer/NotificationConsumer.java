package com.notifyservice.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notifyservice.dto.Notification;
import com.notifyservice.emailNotification.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {
    @Autowired
    private SendEmailService service;
    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "NOTIFICATION", groupId = "task-consumer-group")
    public void consume(Notification notification) {

//        @Value("$(spring.mail.username)")
//        private String fromMail;
//
        // Process the consumed Task message here
        System.out.println("Received Task: " + notification);

        //receipent body subject
//        service.sendEmail(notification.getTo(), notification.getBody(), "boom guyss");
//        service.sendEmail(notification.getTo(), notification.getFrom(), "boom guyss");
//        service.sendEmail(notification.getTo(), notification.getFrom(), "Task Due Updates");

        service.sendEmail(notification.getTo(), notification.getBody(), notification.getSubject());
        // You can add your custom logic to handle the task message
    }
}
