package com.challangeLocaweb.api.services.impl;

import com.challangeLocaweb.api.mails.EmailMessage;
import com.challangeLocaweb.api.mails.WelcomeEmail;
import com.challangeLocaweb.api.services.QueueService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueServiceImpl implements QueueService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void queueEmail(EmailMessage emailMessage) {
        rabbitTemplate.convertAndSend("emailQueue", emailMessage);
    }

    public void queueWelcomeEmail(WelcomeEmail welcomeEmail) {
        rabbitTemplate.convertAndSend("welcomeEmailQueue", welcomeEmail);
    }


}
