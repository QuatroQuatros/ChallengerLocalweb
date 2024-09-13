package com.challangeLocaweb.api.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import com.challangeLocaweb.api.dtos.blacklist.BlackListCreateDTO;
import com.challangeLocaweb.api.dtos.blacklist.BlackListResponseDTO;
import com.challangeLocaweb.api.dtos.blacklist.BlackListUpdateDTO;
import com.challangeLocaweb.api.exceptions.ModelNotFoundException;
import com.challangeLocaweb.api.models.BlackList;
import com.challangeLocaweb.api.repositories.BlackListRepository;
import org.springframework.stereotype.Service;

@Service
public class BlackListServiceImpl extends AbstractCrudService<BlackList, Long, BlackListCreateDTO, BlackListUpdateDTO, BlackListResponseDTO> {

    @Autowired
    private BlackListRepository blackListRepository;

    @Override
    protected JpaRepository<BlackList, Long> getRepository() {
        return blackListRepository;
    }

    @Override
    protected BlackListResponseDTO toResponseDTO(BlackList blackList) {
        return new BlackListResponseDTO(blackList);
    }

    @Override
    protected BlackList toEntity(BlackListCreateDTO dto) {
        BlackList blackList = new BlackList();
        BeanUtils.copyProperties(dto, blackList);
        return blackList;
    }

    @Override
    protected BlackList updateEntity(BlackList blackList, BlackListUpdateDTO updateDto) {
        blackList.setReason(updateDto.reason());
        blackList.setStatus(updateDto.status());
        return blackList;
    }

    @Transactional
    @Override
    public BlackListResponseDTO store(BlackListCreateDTO blackListData) {
        BlackList blackList = toEntity(blackListData);
        return toResponseDTO(getRepository().save(blackList));
    }

    @Transactional
    @Override
    public BlackListResponseDTO update(Long id, BlackListUpdateDTO updateDTO) {
        BlackList blackList = getRepository().findById(id)
                .orElseThrow(() -> new ModelNotFoundException("entity not found"));
        BlackList updatedBlackList = updateEntity(blackList, updateDTO);
        return toResponseDTO(getRepository().save(updatedBlackList));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (!getRepository().existsById(id)) {
            throw new ModelNotFoundException("entity not found");
        }
        getRepository().deleteById(id);
    }

    @Override
    public BlackListResponseDTO getById(Long id) {
        return getRepository().findById(id)
                .map(this::toResponseDTO)
                .orElseThrow(() -> new ModelNotFoundException("entity not found"));
    }

    @Override
    public Page<BlackListResponseDTO> getAll(Pageable pageable) {
        return getRepository().findAll(pageable).map(this::toResponseDTO);
    }
}
