package com.challangeLocaweb.api.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserUpdateDTO(
        @NotBlank(message = "O nome do usuário é obrigatório!")
        String name,

        @NotBlank(message = "O email do usuário é obrigatório!")
        @Email(message = "O email fornecido não é válido!")
        String email
) {
}

