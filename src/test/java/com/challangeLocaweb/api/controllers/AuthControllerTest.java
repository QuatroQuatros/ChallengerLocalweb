package com.challangeLocaweb.api.controllers;

import com.challangeLocaweb.api.dtos.auth.LoginDTO;
import com.challangeLocaweb.api.dtos.user.UserCreateDTO;
import com.challangeLocaweb.api.dtos.user.UserResponseDTO;
import com.challangeLocaweb.api.models.User;
import com.challangeLocaweb.api.services.QueueService;
import com.challangeLocaweb.api.configs.security.TokenService;
import com.challangeLocaweb.api.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Locale;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthenticationManager authManager;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private QueueService queueService;

    @Mock
    private TokenService tokenService;

    @Mock
    private MessageSource messageSource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    void testLogin_Success() throws Exception {
        // Cenário
        LoginDTO loginDTO = new LoginDTO("user@test.com", "password123");
        User user = new User();
        user.setEmail("user@test.com");

        Authentication auth = mock(Authentication.class);
        when(auth.getPrincipal()).thenReturn(user);
        when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);
        when(tokenService.createToken(any(User.class))).thenReturn("mockedToken");
        when(messageSource.getMessage(eq("user.login"), any(), any(Locale.class))).thenReturn("Login bem-sucedido");

        // Execução e Verificação
        mockMvc.perform(post("/api/auth/login")
                        .contentType("application/json")
                        .content("{ \"email\": \"user@test.com\", \"password\": \"password123\" }"))
                .andExpect(status().isOk());

        verify(authManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(tokenService, times(1)).createToken(any(User.class));
        verify(messageSource, times(1)).getMessage(eq("user.login"), any(), any(Locale.class));
    }

    @Test
    void testRegister_Success() throws Exception {
        // Cenário
        UserCreateDTO userCreateDTO = new UserCreateDTO("user@test.com", "Test User", "password123", null);
        UserResponseDTO userResponseDTO = new UserResponseDTO(new User());

        when(userService.store(any(UserCreateDTO.class))).thenReturn(userResponseDTO);
        when(messageSource.getMessage(eq("user.register.successfuly"), any(), any(Locale.class))).thenReturn("Registro bem-sucedido");

        // Execução e Verificação
        mockMvc.perform(post("/api/auth/register")
                        .contentType("application/json")
                        .content("{ \"email\": \"user@test.com\", \"name\": \"Test User\", \"password\": \"password123\" }"))
                .andExpect(status().isCreated());

        verify(userService, times(1)).store(any(UserCreateDTO.class));
        verify(queueService, times(1)).queueEmail(anyString(), anyString(), anyString());
        verify(messageSource, times(1)).getMessage(eq("user.register.successfuly"), any(), any(Locale.class));
    }
}
