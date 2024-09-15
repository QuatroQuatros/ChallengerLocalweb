package com.challangeLocaweb.api.dtos.userpreferences;

import com.challangeLocaweb.api.models.UserPreferences;

import java.time.LocalDateTime;

public record UserPreferencesResponseDTO(
    Long id,
    String theme,
    String language,
    String timezone,
    LocalDateTime updatedAt,
    LocalDateTime createdAt
) {
    public UserPreferencesResponseDTO(UserPreferences userPreferences) {
        this(
            userPreferences.getId(),
            userPreferences.getTheme(),
            userPreferences.getLanguage(),
            userPreferences.getTimezone(),
            userPreferences.getUpdatedAt(),
            userPreferences.getCreatedAt()
        );
    }
}
