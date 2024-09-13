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
    private String content;

    @Serial
    private static final long serialVersionUID = 1L;

    public EmailMessage(String recipient, String subject, String content) {
        this.recipient = recipient;
        this.subject = subject;
        this.content = content;
    }
}
