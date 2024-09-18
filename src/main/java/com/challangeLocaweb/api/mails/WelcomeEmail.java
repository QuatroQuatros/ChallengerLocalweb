package com.challangeLocaweb.api.mails;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Locale;

@Getter
@Setter
public class WelcomeEmail implements Serializable{
    private String userName;
    private String userEmail;
    private Locale locale;


    @Serial
    private static final long serialVersionUID = 1L;

    public WelcomeEmail(String userName, String userEmail, Locale locale) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.locale = locale;
    }

}



