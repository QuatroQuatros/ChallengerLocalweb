package com.challangeLocaweb.api.services;

import com.challangeLocaweb.api.mails.EmailMessage;
import com.challangeLocaweb.api.mails.WelcomeEmail;

public interface QueueConsumerService {

    void processQueue(WelcomeEmail welcomeEmail);
}
