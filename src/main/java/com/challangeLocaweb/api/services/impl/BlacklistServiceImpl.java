package com.challangeLocaweb.api.services.impl;

import com.challangeLocaweb.api.dtos.blacklist.BlacklistCreateDTO;
import com.challangeLocaweb.api.dtos.blacklist.BlacklistResponseDTO;
import com.challangeLocaweb.api.dtos.blacklist.BlacklistUpdateDTO;
import com.challangeLocaweb.api.exceptions.DuplicateEntryException;
import com.challangeLocaweb.api.models.Blacklist;
import com.challangeLocaweb.api.repositories.BlacklistRepository;
import com.challangeLocaweb.api.services.BlacklistService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
public class BlacklistServiceImpl extends AbstractCrudService<Blacklist, Long, BlacklistCreateDTO, BlacklistUpdateDTO, BlacklistResponseDTO> implements BlacklistService {

    @Autowired
    private BlacklistRepository blacklistRepository;

    @Override
    protected JpaRepository<Blacklist, Long> getRepository() {
        return blacklistRepository;
    }

    @Override
    protected BlacklistResponseDTO toResponseDTO(Blacklist blacklist) {
        return new BlacklistResponseDTO(blacklist);
    }


    @Override
    protected Blacklist toEntity(BlacklistCreateDTO blacklistDto) {
        Blacklist blacklist = new Blacklist();
        BeanUtils.copyProperties(blacklistDto, blacklist);

        return blacklist;
    }

    @Override
    protected Blacklist updateEntity(Blacklist blacklist, BlacklistUpdateDTO blacklistData) {
        blacklist.setIpAddress(blacklistData.ipAddress());
        blacklist.setDomain(blacklistData.domain());
        blacklist.setReason(blacklistData.reason());
        blacklist.setStatus(blacklistData.status());
        return blacklist;
    }

    @Override
    public BlacklistResponseDTO store(BlacklistCreateDTO blacklistData) {
        try {
            Blacklist blacklist = new Blacklist();
            BeanUtils.copyProperties(blacklistData, blacklist);
            blacklist.setIpAddress(blacklistData.ipAddress());
            blacklist.setDomain(blacklistData.domain());
            blacklist.setReason(blacklistData.reason());

            blacklist.setStatus(blacklistData.status() == null);


            return new BlacklistResponseDTO(blacklistRepository.save(blacklist));

        } catch (DataIntegrityViolationException e) {
            if (e.getRootCause() instanceof SQLIntegrityConstraintViolationException) {
                throw new DuplicateEntryException("error.ip.duplicate", e);
            }
            throw e;
        }
    }


}
