CREATE TABLE blacklist (
                              id INTEGER PRIMARY KEY AUTO_INCREMENT,
                              ip_address VARCHAR(255) NOT NULL UNIQUE,
                              domain VARCHAR(255) NOT NULL,
                              reason VARCHAR(255) NOT NULL,
                              status BOOLEAN DEFAULT TRUE,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);