package com.challangeLocaweb.api.controllers;

import com.challangeLocaweb.api.dtos.BaseResponseDTO;
import com.challangeLocaweb.api.dtos.user.UserCreateDTO;
import com.challangeLocaweb.api.dtos.user.UserResponseDTO;
import com.challangeLocaweb.api.dtos.user.UserUpdateDTO;
import com.challangeLocaweb.api.exceptions.ModelNotFoundException;
import com.challangeLocaweb.api.helpers.AuthHelpers;
import com.challangeLocaweb.api.models.User;
import com.challangeLocaweb.api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private MessageSource messageSource;

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDTO<Page<UserResponseDTO>> listAll(Pageable page) {
        String message = messageSource.getMessage("user.search.success", null, LocaleContextHolder.getLocale());
        return new BaseResponseDTO<>(
                message,
                service.getAll(page));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDTO<UserResponseDTO> getByID(@PathVariable Long id) {
        try {
            String message = messageSource.getMessage("user.search.success", null, LocaleContextHolder.getLocale());
            return new BaseResponseDTO<>(
                    message,
                    service.getById(id));
        } catch (ModelNotFoundException e) {
            throw new ModelNotFoundException("user.not.found");
        }

    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponseDTO<UserResponseDTO> store(@RequestBody @Valid UserCreateDTO userData) {
        String message = messageSource.getMessage("user.created.success", null, LocaleContextHolder.getLocale());
        return new BaseResponseDTO<>(
                message,
                service.store(userData));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDTO<UserResponseDTO> update(@PathVariable Long id, @RequestBody @Valid UserUpdateDTO userData) {
        AuthHelpers authHelpers = new AuthHelpers();
        if (authHelpers.validateAccess(id)) {
            try {
                String message = messageSource.getMessage("user.updated.success", null,
                        LocaleContextHolder.getLocale());
                return new BaseResponseDTO<>(
                        message,
                        service.update(id, userData));
            } catch (ModelNotFoundException e) {
                throw new ModelNotFoundException("user.not.found");
            }
        } else {
            throw new AccessDeniedException("user.update.permission.denied");
        }

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BaseResponseDTO<Object> delete(@PathVariable Long id) {
        AuthHelpers authHelpers = new AuthHelpers();
        if (authHelpers.validateAccess(id)) {
            try {
                service.delete(id);
                String message = messageSource.getMessage("user.deleted.success", null,
                        LocaleContextHolder.getLocale());
                return new BaseResponseDTO<>(message, null);
            } catch (ModelNotFoundException e) {
                throw new ModelNotFoundException("user.not.found");
            }
        } else {
            throw new AccessDeniedException("user.delete.permission.denied");
        }

    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDTO<UserResponseDTO> getMyUser() {
        AuthHelpers authHelpers = new AuthHelpers();
        try {
            User user = authHelpers.getUser();
            String message = messageSource.getMessage("user.recovered.success", null, LocaleContextHolder.getLocale());
            return new BaseResponseDTO<>(
                    message,
                    new UserResponseDTO(user));
        } catch (ModelNotFoundException e) {
            throw new ModelNotFoundException("user.not.found");
        }

    }
}
