package com.challangeLocaweb.api.services;

public interface QueueService {

    void queueEmail(String recipient, String subject, String message);
}
