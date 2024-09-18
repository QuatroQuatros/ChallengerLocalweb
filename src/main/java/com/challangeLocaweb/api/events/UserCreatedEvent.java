package com.challangeLocaweb.api.events;

import com.challangeLocaweb.api.models.User;
import lombok.Getter;

@Getter
public class UserCreatedEvent {

    private final User user;

    public UserCreatedEvent(User user) {
        this.user = user;
    }

}
