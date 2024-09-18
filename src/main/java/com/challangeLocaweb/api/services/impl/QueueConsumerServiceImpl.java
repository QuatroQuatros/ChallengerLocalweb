package com.challangeLocaweb.api.services.impl;

import jakarta.mail.internet.MimeMessage;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import com.challangeLocaweb.api.mails.WelcomeEmail;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Locale;

@Service
public class QueueConsumerServiceImpl implements com.challangeLocaweb.api.services.QueueConsumerService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private MessageSource messageSource;

    @Value("${spring.mail.username}")
    private String sender;

    @SneakyThrows
    @RabbitListener(queues = "welcomeEmailQueue")
    public void processQueue(WelcomeEmail welcomeEmail) {
        try {
            String emailContent = generateEmailContent(welcomeEmail.getUserName(), welcomeEmail.getLocale());

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            String subject = messageSource.getMessage("welcome.email.subject", null, welcomeEmail.getLocale());
            helper.setFrom(sender);
            helper.setTo(welcomeEmail.getUserEmail());
            helper.setSubject(subject);
            helper.setText(emailContent, true);

            javaMailSender.send(mimeMessage);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateEmailContent(String userName, Locale locale) {
        Context context = new Context(locale);
        context.setVariable("userName", userName);
        if(locale.equals(Locale.forLanguageTag("en"))){
            return templateEngine.process("welcome_en", context);
        }
        else if(locale.equals(Locale.forLanguageTag("pt-BR"))){
            return templateEngine.process("welcome_pt-br", context);
        }
        else{
            return templateEngine.process("welcome_en", context);
        }


    }
}
