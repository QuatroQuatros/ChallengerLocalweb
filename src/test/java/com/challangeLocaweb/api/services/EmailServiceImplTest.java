package com.challangeLocaweb.api.services;

import com.challangeLocaweb.api.dtos.emails.EmailCreateDTO;
import com.challangeLocaweb.api.dtos.emails.EmailResponseDTO;
import com.challangeLocaweb.api.exceptions.DuplicateEntryException;
import com.challangeLocaweb.api.helpers.AuthHelpers;
import com.challangeLocaweb.api.models.Email;
import com.challangeLocaweb.api.models.EmailSent;
import com.challangeLocaweb.api.models.User;
import com.challangeLocaweb.api.repositories.EmailRepository;
import com.challangeLocaweb.api.repositories.EmailSentRepository;
import com.challangeLocaweb.api.services.impl.EmailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.SQLIntegrityConstraintViolationException;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmailServiceImplTest {

    @Mock
    private EmailRepository emailRepository;

    @Mock
    private EmailSentRepository emailSentRepository;

    @Mock
    private AuthHelpers authHelpers;

    @InjectMocks
    private EmailServiceImpl emailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testStoreEmailSuccess() {
        // Arrange
        EmailCreateDTO emailCreateDTO = new EmailCreateDTO("test@example.com", "Test Subject", "Test content", "Test plain", false);
        Email email = new Email();
        email.setId(1L);

        when(emailRepository.save(any(Email.class))).thenReturn(email);

        User user = new User();
        user.setId(1L);
        when(authHelpers.getUser()).thenReturn(user);

        EmailSent emailSent = new EmailSent();
        emailSent.setEmail(email);
        emailSent.setUser(user);
        when(emailSentRepository.save(any(EmailSent.class))).thenReturn(emailSent);

        EmailResponseDTO response = emailService.store(emailCreateDTO);

        assertNotNull(response);
        assertEquals(1L, response.id());
        verify(emailRepository, times(1)).save(any(Email.class));
        verify(emailSentRepository, times(1)).save(any(EmailSent.class));
    }

    @Test
    void testStoreEmailDuplicateEntryException() {
        EmailCreateDTO emailCreateDTO = new EmailCreateDTO("test@example.com", "Test Subject", "Test content", "Test plain", false);
        DataIntegrityViolationException exception = new DataIntegrityViolationException("Duplicate Entry", new SQLIntegrityConstraintViolationException());

        when(emailRepository.save(any(Email.class))).thenThrow(exception);

        DuplicateEntryException thrown = assertThrows(DuplicateEntryException.class, () -> {
            emailService.store(emailCreateDTO);
        });

        assertEquals("error.email.duplicate", thrown.getMessage());
        verify(emailRepository, times(1)).save(any(Email.class));
        verify(emailSentRepository, times(0)).save(any(EmailSent.class));
    }

}

