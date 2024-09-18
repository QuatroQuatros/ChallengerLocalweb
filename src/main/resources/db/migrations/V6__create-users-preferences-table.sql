CREATE TABLE user_preferences (
                                  id INTEGER PRIMARY KEY AUTO_INCREMENT,
                                  user_id INTEGER NOT NULL,
                                  theme VARCHAR(255) NOT NULL DEFAULT 'system_default',
                                  language VARCHAR(255) NOT NULL DEFAULT 'system_default',
                                  receive_push_notifications BOOLEAN DEFAULT false,
                                  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

ALTER TABLE user_preferences ADD CONSTRAINT FK_user_preferences
    FOREIGN KEY (user_id)
        REFERENCES users(id);
