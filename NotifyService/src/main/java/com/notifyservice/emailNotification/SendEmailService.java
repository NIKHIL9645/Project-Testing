package com.notifyservice.emailNotification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {

    @Autowired
    private JavaMailSender mailSender;


    @Value("$(spring.mail.username)")
    private String fromMailId;


    public void sendEmail(String recipient, String body, String subject) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setFrom("nikhilnathe2@gmail.com");

        simpleMailMessage.setFrom(fromMailId);
        simpleMailMessage.setTo(recipient);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        System.out.println("222222222");
        System.out.println(simpleMailMessage);

        mailSender.send(simpleMailMessage);

        System.out.println("send success");
    }
}

//
//}
