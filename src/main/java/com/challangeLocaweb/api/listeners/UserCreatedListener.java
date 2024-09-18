package com.challangeLocaweb.api.listeners;

import com.challangeLocaweb.api.events.UserCreatedEvent;
import com.challangeLocaweb.api.mails.WelcomeEmail;
import com.challangeLocaweb.api.models.User;
import com.challangeLocaweb.api.services.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class UserCreatedListener {

    @Autowired
    private QueueService queueService;


    @EventListener
    public void handleUserCreatedEvent(UserCreatedEvent event) {
        User user = event.getUser();
        Locale locale = LocaleContextHolder.getLocale();
        sendWelcomeEmail(user, locale);
    }

    public void sendWelcomeEmail(User user, Locale locale) {

        WelcomeEmail welcomeEmail = new WelcomeEmail(user.getName(), user.getEmail(), locale);

        queueService.queueWelcomeEmail(welcomeEmail);
    }
}
