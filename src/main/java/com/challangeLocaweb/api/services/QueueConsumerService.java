package com.challangeLocaweb.api.services;

import com.challangeLocaweb.api.mails.EmailMessage;

public interface QueueConsumerService {

    void processQueue(EmailMessage emailMessage);
}
