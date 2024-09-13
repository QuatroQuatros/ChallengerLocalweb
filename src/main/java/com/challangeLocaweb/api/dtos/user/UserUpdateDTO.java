package com.challangeLocaweb.api.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateDTO(
        @NotBlank(message = "{user.name.notblank}")
        String name,

        @NotBlank(message = "{user.email.notblank}")
        @Email(message = "{user.email.invalid}")
        String email,

        String photo
) {
}

