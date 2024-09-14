package com.challangeLocaweb.api.services.impl;

import com.challangeLocaweb.api.dtos.blacklist.BlacklistCreateDTO;
import com.challangeLocaweb.api.dtos.blacklist.BlacklistResponseDTO;
import com.challangeLocaweb.api.dtos.blacklist.BlacklistUpdateDTO;
import com.challangeLocaweb.api.exceptions.DuplicateEntryException;
import com.challangeLocaweb.api.models.Blacklist;
import com.challangeLocaweb.api.repositories.BlacklistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.SQLIntegrityConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BlacklistServiceImplTest {

    @Mock
    private BlacklistRepository blacklistRepository;

    @InjectMocks
    private BlacklistServiceImplTestable blacklistService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testStore_Success() {
        BlacklistCreateDTO blacklistCreateDTO = new BlacklistCreateDTO("192.168.0.1", "example.com", "Spam", true);
        Blacklist blacklist = new Blacklist();
        BeanUtils.copyProperties(blacklistCreateDTO, blacklist);
        when(blacklistRepository.save(any(Blacklist.class))).thenReturn(blacklist);

        BlacklistResponseDTO response = blacklistService.store(blacklistCreateDTO);

        assertNotNull(response);
        assertEquals(blacklistCreateDTO.ipAddress(), response.ipAddress());
        verify(blacklistRepository, times(1)).save(any(Blacklist.class));
    }

    @Test
    void testStore_DuplicateEntryException() {
        BlacklistCreateDTO blacklistCreateDTO = new BlacklistCreateDTO("192.168.0.1", "example.com", "Spam", true);
        DataIntegrityViolationException exception = new DataIntegrityViolationException("Duplicate Entry", new SQLIntegrityConstraintViolationException());

        when(blacklistRepository.save(any(Blacklist.class))).thenThrow(exception);

        assertThrows(DuplicateEntryException.class, () -> blacklistService.store(blacklistCreateDTO));
        verify(blacklistRepository, times(1)).save(any(Blacklist.class));
    }

    @Test
    void testToResponseDTO() {
        Blacklist blacklist = new Blacklist();
        blacklist.setId(1L);
        blacklist.setIpAddress("192.168.0.1");
        blacklist.setDomain("example.com");
        blacklist.setReason("Spam");
        blacklist.setStatus(true);

        BlacklistResponseDTO responseDTO = blacklistService.toResponseDTO(blacklist);

        assertNotNull(responseDTO);
        assertEquals(blacklist.getId(), responseDTO.id());
        assertEquals(blacklist.getIpAddress(), responseDTO.ipAddress());
        assertEquals(blacklist.getDomain(), responseDTO.domain());
    }

    @Test
    void testToEntity() {
        BlacklistCreateDTO blacklistCreateDTO = new BlacklistCreateDTO("192.168.0.1", "example.com", "Spam", true);

        Blacklist blacklist = blacklistService.toEntity(blacklistCreateDTO);

        assertNotNull(blacklist);
        assertEquals(blacklistCreateDTO.ipAddress(), blacklist.getIpAddress());
        assertEquals(blacklistCreateDTO.domain(), blacklist.getDomain());
        assertEquals(blacklistCreateDTO.reason(), blacklist.getReason());
    }

    @Test
    void testUpdateEntity() {
        Blacklist blacklist = new Blacklist();
        BlacklistUpdateDTO blacklistUpdateDTO = new BlacklistUpdateDTO("10.0.0.1", "updated.com", "Updated reason", false);

        Blacklist updatedBlacklist = blacklistService.updateEntity(blacklist, blacklistUpdateDTO);

        assertNotNull(updatedBlacklist);
        assertEquals(blacklistUpdateDTO.ipAddress(), updatedBlacklist.getIpAddress());
        assertEquals(blacklistUpdateDTO.domain(), updatedBlacklist.getDomain());
        assertEquals(blacklistUpdateDTO.reason(), updatedBlacklist.getReason());
        assertEquals(blacklistUpdateDTO.status(), updatedBlacklist.getStatus());
    }

    static class BlacklistServiceImplTestable extends BlacklistServiceImpl {
        @Override
        public BlacklistResponseDTO toResponseDTO(Blacklist blacklist) {
            return super.toResponseDTO(blacklist);
        }

        @Override
        public Blacklist toEntity(BlacklistCreateDTO blacklistCreateDTO) {
            return super.toEntity(blacklistCreateDTO);
        }

        @Override
        public Blacklist updateEntity(Blacklist blacklist, BlacklistUpdateDTO blacklistUpdateDTO) {
            return super.updateEntity(blacklist, blacklistUpdateDTO);
        }
    }
}
