package com.challangeLocaweb.api.mails;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class EmailMessage implements Serializable {

    private String recipient;
    private String subject;
    private String message;

    @Serial
    private static final long serialVersionUID = 1L;

    public EmailMessage(String recipient, String subject, String message) {
        this.recipient = recipient;
        this.subject = subject;
        this.message = message;
    }
}
