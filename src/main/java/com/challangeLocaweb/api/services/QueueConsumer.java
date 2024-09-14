package com.challangeLocaweb.api.services;

import com.challangeLocaweb.api.mails.EmailMessage;

public interface QueueConsumer {

    void processQueue(EmailMessage emailMessage);
}
