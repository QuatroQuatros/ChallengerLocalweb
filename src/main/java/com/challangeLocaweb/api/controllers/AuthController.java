package com.challangeLocaweb.api.controllers;

import com.challangeLocaweb.api.configs.security.TokenService;
import com.challangeLocaweb.api.dtos.BaseResponseDTO;
import com.challangeLocaweb.api.dtos.auth.LoginDTO;
import com.challangeLocaweb.api.dtos.auth.LoginResponseDTO;
import com.challangeLocaweb.api.dtos.user.UserCreateDTO;
import com.challangeLocaweb.api.dtos.user.UserResponseDTO;
import com.challangeLocaweb.api.models.User;
import com.challangeLocaweb.api.services.EmailService;
import com.challangeLocaweb.api.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserServiceImpl service;
    @Autowired
    private EmailService emailService;


    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public BaseResponseDTO<LoginResponseDTO> login(@RequestBody @Valid LoginDTO userData){
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(
                        userData.email(),
                        userData.password()
                );

        Authentication auth = authManager.authenticate(usernamePassword);

        String token = tokenService.createToken((User)auth.getPrincipal());
        String message = messageSource.getMessage("user.login", null, LocaleContextHolder.getLocale());
        return new BaseResponseDTO<>(
                message,
                new LoginResponseDTO(
                        new UserResponseDTO((User)auth.getPrincipal())
                        ,token
                )

        );
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponseDTO<UserResponseDTO> register(@RequestBody @Valid UserCreateDTO userData){
        UserResponseDTO newUser = service.store(userData);

        emailService.queueEmail(userData.email(), "Welcome to our platform", "You are registered!");

        String message = messageSource.getMessage("user.register.successfuly", null, LocaleContextHolder.getLocale());
        return new BaseResponseDTO<>(
                message,
                newUser
        );

    }
}

