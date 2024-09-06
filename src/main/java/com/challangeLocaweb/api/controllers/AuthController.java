package com.challangeLocaweb.api.controllers;

import com.challangeLocaweb.api.config.security.TokenService;
import com.challangeLocaweb.api.dtos.BaseResponseDTO;
import com.challangeLocaweb.api.dtos.auth.LoginDTO;
import com.challangeLocaweb.api.dtos.auth.LoginResponseDTO;
import com.challangeLocaweb.api.dtos.user.UserCreateDTO;
import com.challangeLocaweb.api.dtos.user.UserResponseDTO;
import com.challangeLocaweb.api.models.User;
import com.challangeLocaweb.api.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserServiceImpl service;

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

        return new BaseResponseDTO<>(
                "You are logged in!",
                new LoginResponseDTO(
                        new UserResponseDTO((User)auth.getPrincipal())
                        ,token
                )

        );
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponseDTO<UserResponseDTO> register(@RequestBody @Valid UserCreateDTO userData){
        return new BaseResponseDTO<>(
                "user register successfuly!",
                service.store(userData)
        );

    }
}

