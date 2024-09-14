CREATE TABLE emails_sent (
                             id INTEGER PRIMARY KEY AUTO_INCREMENT,
                             user_id INTEGER,
                             email_id INTEGER,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

ALTER TABLE emails_sent ADD CONSTRAINT FK_emails_sent_user
    FOREIGN KEY (user_id)
        REFERENCES users(id);

ALTER TABLE emails_sent ADD CONSTRAINT FK_emails_sent_email
    FOREIGN KEY (email_id)
        REFERENCES emails(id);