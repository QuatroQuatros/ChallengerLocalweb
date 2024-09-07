package com.challangeLocaweb.api.services;

public interface EmailService {

    void queueEmail(String recipient, String subject, String message);
}
