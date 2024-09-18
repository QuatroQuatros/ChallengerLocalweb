package com.challangeLocaweb.api.dtos.user;

import com.challangeLocaweb.api.dtos.user.preferences.UserPreferencesCreateDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateDTO(

        @NotBlank(message = "{user.name.notblank}")
        String name,

        @NotBlank(message = "{user.email.notblank}")
        @Email(message = "{user.email.invalid}")
        String email,

        String photo,

        @NotBlank(message = "{user.password.notblank}")
        @Size(min = 6, message = "{user.password.size}")
        String password,

        UserPreferencesCreateDTO preferences
){}
