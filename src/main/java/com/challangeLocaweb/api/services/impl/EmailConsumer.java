package com.challangeLocaweb.api.services.impl;

import com.challangeLocaweb.api.mails.EmailMessage;
import com.challangeLocaweb.api.services.QueueConsumer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer implements QueueConsumer {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @RabbitListener(queues = "emailQueue")
    public void processQueue(EmailMessage emailMessage) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setTo(emailMessage.getRecipient());
            simpleMailMessage.setSubject(emailMessage.getSubject());
            simpleMailMessage.setText(emailMessage.getContent());
            javaMailSender.send(simpleMailMessage);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
