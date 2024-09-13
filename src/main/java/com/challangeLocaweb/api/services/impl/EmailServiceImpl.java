package com.challangeLocaweb.api.services.impl;

import com.challangeLocaweb.api.mails.EmailMessage;
import com.challangeLocaweb.api.services.EmailService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void queueEmail(String recipient, String subject, String content) {
        EmailMessage emailMessage = new EmailMessage(recipient, subject, content);
        rabbitTemplate.convertAndSend("emailQueue", emailMessage);
    }
}
