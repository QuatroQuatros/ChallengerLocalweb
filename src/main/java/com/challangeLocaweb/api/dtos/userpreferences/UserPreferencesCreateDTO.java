package com.challangeLocaweb.api.dtos.userpreferences;

import jakarta.validation.constraints.NotBlank;

public record UserPreferencesCreateDTO(

    @NotBlank(message = "user.preferences.theme.notblank")
    String theme,

    @NotBlank(message = "user.preferences.language.notblank")
    String language,

    @NotBlank(message = "user.preferences.timezone.notblank")
    String timezone
) {
}
