package com.challangeLocaweb.api.services;

import com.challangeLocaweb.api.mails.EmailMessage;
import com.challangeLocaweb.api.mails.WelcomeEmail;
import com.challangeLocaweb.api.models.Email;

public interface QueueService {

    void queueEmail(EmailMessage emailMessage);
    void queueWelcomeEmail(WelcomeEmail welcomeEmail);
}
