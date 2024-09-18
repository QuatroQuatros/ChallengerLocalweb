package com.challangeLocaweb.api.dtos.user;

import com.challangeLocaweb.api.dtos.BaseResponseDTO;
import com.challangeLocaweb.api.dtos.user.preferences.UserPreferencesResponseDTO;
import com.challangeLocaweb.api.models.User;

import java.time.LocalDateTime;


public record UserResponseDTO(
        Long id,
        String name,
        String email,
        String photo,
        UserPreferencesResponseDTO preferences,
        LocalDateTime created_at,
        LocalDateTime updated_at
) {
    public  UserResponseDTO(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhoto(),
                user.getPreferences() != null ? new UserPreferencesResponseDTO(user.getPreferences()) : null,
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public BaseResponseDTO<UserResponseDTO> toResponseDto() {
        return new BaseResponseDTO<>("fetch users successfully", this);
    }
}
