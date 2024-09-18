package com.challangeLocaweb.api.dtos.user.preferences;

import com.challangeLocaweb.api.models.UserPreferences;

import java.time.LocalDateTime;

public record UserPreferencesResponseDTO(
    Long id,
    String theme,
    String language,
    LocalDateTime created_at,
    LocalDateTime updated_at

) {
    public UserPreferencesResponseDTO(UserPreferences userPreferences) {
        this(
            userPreferences.getId(),
            userPreferences.getTheme(),
            userPreferences.getLanguage(),
            userPreferences.getCreatedAt(),
            userPreferences.getUpdatedAt()

        );
    }
}
