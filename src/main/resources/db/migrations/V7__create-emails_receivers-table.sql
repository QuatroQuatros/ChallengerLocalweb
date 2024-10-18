CREATE TABLE emails_receivers (
                                  id INTEGER PRIMARY KEY AUTO_INCREMENT,
                                  email_id INTEGER,
                                  receiver VARCHAR(255) NOT NULL,
                                  is_cco BOOLEAN DEFAULT FALSE,
                                  is_cc BOOLEAN DEFAULT FALSE,
                                  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

ALTER TABLE emails_receivers ADD CONSTRAINT FK_emails_recievers_2
    FOREIGN KEY (email_id)
        REFERENCES emails(id);