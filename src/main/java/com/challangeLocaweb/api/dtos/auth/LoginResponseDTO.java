package com.challangeLocaweb.api.dtos.auth;

import com.challangeLocaweb.api.dtos.user.UserResponseDTO;

public record LoginResponseDTO(
        UserResponseDTO user,
        String token
) {
}
