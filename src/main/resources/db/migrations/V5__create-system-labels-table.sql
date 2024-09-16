CREATE TABLE system_labels (
                              id INTEGER PRIMARY KEY AUTO_INCREMENT,
                              label_name VARCHAR(255) NOT NULL,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);