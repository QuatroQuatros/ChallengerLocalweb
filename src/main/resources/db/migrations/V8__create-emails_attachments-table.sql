CREATE TABLE emails_attachments (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    email_id INTEGER,
    file_name VARCHAR(255) NOT NULL,
    file_path VARCHAR(255) NOT NULL,
    mime_type VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

ALTER TABLE emails_attachments ADD CONSTRAINT FK_emails_attachments_2
    FOREIGN KEY (email_id)
        REFERENCES emails(id);