package com.challangeLocaweb.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challangeLocaweb.api.dtos.BaseResponseDTO;
import com.challangeLocaweb.api.dtos.user.preferences.UserPreferencesResponseDTO;
import com.challangeLocaweb.api.dtos.user.preferences.UserPreferencesUpdateDTO;
import com.challangeLocaweb.api.exceptions.ModelNotFoundException;
import com.challangeLocaweb.api.services.UserPreferencesService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/preferences")
public class UserPreferencesController {

    @Autowired
    private UserPreferencesService service;

    @Autowired
    private MessageSource messageSource;

    public BaseResponseDTO<UserPreferencesResponseDTO> getUserPreferencesByUser(){
        try{
            String message = messageSource.getMessage("user.preferences.not.found", null, LocaleContextHolder.getLocale());
            return new BaseResponseDTO<>(message, service.getByUser());
        } catch(ModelNotFoundException e) {
            throw new ModelNotFoundException("user.preferences.not.found");
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDTO<UserPreferencesResponseDTO> getUserPreferencesById(@PathVariable Long id){
        try{
            String message = messageSource.getMessage("user.preferences.not.found", null, LocaleContextHolder.getLocale());
            return new BaseResponseDTO<>(message, service.getById(id));
        } catch (ModelNotFoundException e){
            throw new ModelNotFoundException("user.preferences.not.found");
        }
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDTO<UserPreferencesResponseDTO> updateUserPreferencesDto(@PathVariable Long id, @RequestBody @Valid UserPreferencesUpdateDTO userPreferencesUpdateDTO){
        try{
            String message = messageSource.getMessage("user.preferences.updated.sucess", null, LocaleContextHolder.getLocale());
            return new BaseResponseDTO<>(message, service.update(id, userPreferencesUpdateDTO));
        } catch (ModelNotFoundException e){
            throw new ModelNotFoundException("user.preferences.not.found");
        }
    }
}
