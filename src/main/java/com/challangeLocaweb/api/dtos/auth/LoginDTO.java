package com.challangeLocaweb.api.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO(
        @NotBlank(message = "The field email is required!")
        @Email(message = "The given email is not valid!")
        String email,

        @NotBlank(message = "Password is required!")
        @Size(min = 6, message = "Password must be at least 6 characters")
        String password
) {
}
