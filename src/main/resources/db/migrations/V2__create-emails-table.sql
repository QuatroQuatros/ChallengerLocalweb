CREATE TABLE emails (
                        id INTEGER PRIMARY KEY AUTO_INCREMENT,
                        sender VARCHAR(255) NOT NULL,
                        subject VARCHAR(255) NOT NULL,
                        content_html VARCHAR(255),
                        content_plain VARCHAR(255),
                        is_confidential BOOLEAN DEFAULT FALSE,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);