package com.challangeLocaweb.api.services;

import com.challangeLocaweb.api.dtos.user.UserCreateDTO;
import com.challangeLocaweb.api.dtos.user.UserResponseDTO;
import com.challangeLocaweb.api.exceptions.DuplicateEntryException;
import com.challangeLocaweb.api.models.User;
import com.challangeLocaweb.api.repositories.UserRepository;
import com.challangeLocaweb.api.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.SQLIntegrityConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testStore_Success() {
        UserCreateDTO userCreateDTO = new UserCreateDTO("user@test.com", "Test User", null, "password123");
        User user = new User();
        user.setEmail("user@test.com");
        user.setName("Test User");
        user.setPassword("hashedPassword");

        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponseDTO response = userService.store(userCreateDTO);

        assertNotNull(response);
        assertEquals("Test User", response.name());
        assertEquals("user@test.com", response.email());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testStore_DuplicateEmail() {
        UserCreateDTO userCreateDTO = new UserCreateDTO("user@test.com", "Test User", null, "password123");

        SQLIntegrityConstraintViolationException sqlException = new SQLIntegrityConstraintViolationException("Duplicate entry");
        DataIntegrityViolationException dataIntegrityViolationException = new DataIntegrityViolationException("Duplicate entry", sqlException);

        when(userRepository.save(any(User.class))).thenThrow(dataIntegrityViolationException);

        assertThrows(DuplicateEntryException.class, () -> userService.store(userCreateDTO));
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testStore_NoPhotoProvided() {
        UserCreateDTO userCreateDTO = new UserCreateDTO("user@test.com", "Test User", null,"password123");
        User user = new User();
        user.setEmail("user@test.com");
        user.setName("Test User");
        user.setPassword("hashedPassword");
        user.setPhoto("https://ui-avatars.com/api/?background=random&name=Test+User");

        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponseDTO response = userService.store(userCreateDTO);

        assertNotNull(response);
        assertEquals("Test User", response.name());
        assertEquals("https://ui-avatars.com/api/?background=random&name=Test+User", response.photo());
        verify(userRepository, times(1)).save(any(User.class));
    }

}