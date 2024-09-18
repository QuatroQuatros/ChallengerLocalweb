package com.challangeLocaweb.api.services;

import com.challangeLocaweb.api.dtos.user.preferences.UserPreferencesCreateDTO;
import com.challangeLocaweb.api.dtos.user.preferences.UserPreferencesResponseDTO;
import com.challangeLocaweb.api.dtos.user.preferences.UserPreferencesUpdateDTO;
import com.challangeLocaweb.api.models.UserPreferences;

public interface UserPreferencesService extends CRUDInterface<UserPreferences, Long, UserPreferencesCreateDTO, UserPreferencesUpdateDTO, UserPreferencesResponseDTO> {
    UserPreferencesResponseDTO getByUser();
}
