package com.challangeLocaweb.api.dtos.user.preferences;

import jakarta.validation.constraints.NotBlank;

public record UserPreferencesCreateDTO(

    @NotBlank(message = "user.preferences.theme.notblank")
    String theme,

    @NotBlank(message = "user.preferences.language.notblank")
    String language

) {
}
