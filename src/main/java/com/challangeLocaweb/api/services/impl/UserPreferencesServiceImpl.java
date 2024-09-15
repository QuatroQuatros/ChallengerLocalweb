package com.challangeLocaweb.api.services.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.challangeLocaweb.api.dtos.userpreferences.UserPreferencesCreateDTO;
import com.challangeLocaweb.api.dtos.userpreferences.UserPreferencesResponseDTO;
import com.challangeLocaweb.api.dtos.userpreferences.UserPreferencesUpdateDTO;
import com.challangeLocaweb.api.exceptions.ModelNotFoundException;
import com.challangeLocaweb.api.exceptions.UserNotAuthenticatedException;
import com.challangeLocaweb.api.helpers.AuthHelpers;
import com.challangeLocaweb.api.models.User;
import com.challangeLocaweb.api.models.UserPreferences;
import com.challangeLocaweb.api.repositories.UserPreferencesRepository;
import com.challangeLocaweb.api.services.UserPreferencesService;

@Service
public class UserPreferencesServiceImpl extends AbstractCrudService<UserPreferences, Long, UserPreferencesCreateDTO, UserPreferencesUpdateDTO, UserPreferencesResponseDTO> implements UserPreferencesService  {

    @Autowired
    private UserPreferencesRepository userPreferencesRepository;

    @Autowired
    private AuthHelpers authHelpers;

    @Override
    protected JpaRepository<UserPreferences, Long> getRepository() {
        return userPreferencesRepository;
    }

    @Override
    protected UserPreferencesResponseDTO toResponseDTO(UserPreferences entity) {
        return new UserPreferencesResponseDTO(entity);
    }

    @Override
    protected UserPreferences toEntity(UserPreferencesCreateDTO dto) {
        UserPreferences userPreferences = new UserPreferences();
        BeanUtils.copyProperties(dto, userPreferences);

        return userPreferences;
    }

    @Override
    protected UserPreferences updateEntity(UserPreferences existingEntity, UserPreferencesUpdateDTO updateDto) {
        existingEntity.setTheme(updateDto.theme());
        existingEntity.setLanguage(updateDto.language());
        existingEntity.setTimezone(updateDto.timezone());
        return existingEntity;
    }

    @Override
    public UserPreferencesResponseDTO store(UserPreferencesCreateDTO userPreferencesCreateDTO){
        UserPreferences userPreferences = new UserPreferences();
        BeanUtils.copyProperties(userPreferencesCreateDTO, userPreferences);
        User user = authHelpers.getUser();
        userPreferences.setUser(user);
        return new UserPreferencesResponseDTO(userPreferencesRepository.save(userPreferences));
    }

    @Override
    public UserPreferencesResponseDTO update(Long id, UserPreferencesUpdateDTO userPreferencesUpdateDTO){

        Long idUser = authHelpers.getUserId();

        Optional<UserPreferences> userPreferencesOptional = userPreferencesRepository.findById(id);
        if (userPreferencesOptional.isPresent()) {
            User user = userPreferencesOptional.get().getUser();
            if (user.getId().equals(idUser)) {
                UserPreferences userPreferences = updateEntity(userPreferencesOptional.get(), userPreferencesUpdateDTO);
                return new UserPreferencesResponseDTO(userPreferencesRepository.save(userPreferences));
            } else {
                throw new UserNotAuthenticatedException("user.update.permission.denied");
            }
        } else {
            throw new ModelNotFoundException("user.not.found");
        }
    }

    @Override
    public UserPreferencesResponseDTO getById(Long id){
        Optional<UserPreferences> userPreferencesOptional = userPreferencesRepository.findById(id);
        if (userPreferencesOptional.isPresent()) {
            return toResponseDTO(userPreferencesOptional.get());
        } else {
            throw new ModelNotFoundException("user.preferences.not.found");
        }
    }

    @Override
    public UserPreferencesResponseDTO getByUser() {
        User user = authHelpers.getUser();
        Optional<UserPreferences> userPreferencesOptional = userPreferencesRepository.findByUser(user);
        if (userPreferencesOptional.isPresent()) {
            return toResponseDTO(userPreferencesOptional.get());
        } else {
            throw new ModelNotFoundException("user.preferences.not.found");
        }
    }
}
