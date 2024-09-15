package com.challangeLocaweb.api.services;

import com.challangeLocaweb.api.dtos.userpreferences.UserPreferencesCreateDTO;
import com.challangeLocaweb.api.dtos.userpreferences.UserPreferencesResponseDTO;
import com.challangeLocaweb.api.dtos.userpreferences.UserPreferencesUpdateDTO;
import com.challangeLocaweb.api.models.UserPreferences;

public interface UserPreferencesService extends CRUDInterface<UserPreferences, Long, UserPreferencesCreateDTO, UserPreferencesUpdateDTO, UserPreferencesResponseDTO> {
    UserPreferencesResponseDTO getByUser();
}
