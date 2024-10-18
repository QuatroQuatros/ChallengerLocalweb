package com.challangeLocaweb.api.dtos.user;

import com.challangeLocaweb.api.dtos.user.preferences.UserPreferencesCreateDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Null;

public record UserUpdateDTO(
                String name,

                @Email(message = "{user.email.invalid}") String email,

                @Null String photo,

                UserPreferencesCreateDTO preferences) {
}
