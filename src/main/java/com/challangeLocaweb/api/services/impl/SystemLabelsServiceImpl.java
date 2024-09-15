package com.challangeLocaweb.api.services.impl;

import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.challangeLocaweb.api.dtos.systemlabels.SystemLabelsCreateDTO;
import com.challangeLocaweb.api.dtos.systemlabels.SystemLabelsResponseDTO;
import com.challangeLocaweb.api.dtos.systemlabels.SystemLabelsUpdateDTO;
import com.challangeLocaweb.api.exceptions.ModelNotFoundException;
import com.challangeLocaweb.api.models.SystemLabels;
import com.challangeLocaweb.api.repositories.SystemLabelsRepository;
import com.challangeLocaweb.api.services.SystemLabelsService;

@Service
public class SystemLabelsServiceImpl extends AbstractCrudService<SystemLabels, Long, SystemLabelsCreateDTO, SystemLabelsUpdateDTO, SystemLabelsResponseDTO> implements SystemLabelsService {

    @Autowired
    private SystemLabelsRepository systemLabelsRepository;

    @Override
    protected JpaRepository<SystemLabels, Long> getRepository() {
        return systemLabelsRepository;
    }

    @Override
    protected SystemLabelsResponseDTO toResponseDTO(SystemLabels entity) {
        return new SystemLabelsResponseDTO(entity);
    }

    @Override
    protected SystemLabels toEntity(SystemLabelsCreateDTO dto) {
        SystemLabels systemLabels = new SystemLabels();
        BeanUtils.copyProperties(dto, systemLabels);
        return systemLabels;
    }

    @Override
    protected SystemLabels updateEntity(SystemLabels existingEntity, SystemLabelsUpdateDTO updateDto) {
        existingEntity.setLabelName(updateDto.labelName());
        return existingEntity;
    }

    @Override
    public SystemLabelsResponseDTO store(SystemLabelsCreateDTO systemLabelsCreateDTO){
        SystemLabels systemLabels = toEntity(systemLabelsCreateDTO);
        return new SystemLabelsResponseDTO(getRepository().save(systemLabels));
    }

    public SystemLabelsResponseDTO update(Long id, SystemLabelsUpdateDTO updateDto) throws ModelNotFoundException {
        Optional<SystemLabels> entityOptional = getRepository().findById(id);
        if (entityOptional.isPresent()) {
            SystemLabels updatedEntity = updateEntity(entityOptional.get(), updateDto);
            return toResponseDTO(getRepository().save(updatedEntity));

        } else {
            throw new ModelNotFoundException("error.entity.not.found");
        }
    }

    @Override
    public Page<SystemLabelsResponseDTO> getAll(Pageable paginacao) {
        return getRepository().findAll(paginacao).map(this::toResponseDTO);
    }

    @Override
    public SystemLabelsResponseDTO getById(Long id) throws ModelNotFoundException {
        Optional<SystemLabels> entityOptional = getRepository().findById(id);
        if (entityOptional.isPresent()) {
            return toResponseDTO(entityOptional.get());
        } else {
            throw new ModelNotFoundException("error.entity.not.found");
        }
    }

    @Override
    public void delete(Long id) {
        if (!getRepository().existsById(id)) {
            throw new ModelNotFoundException("error.entity.not.found");
        }
        getRepository().deleteById(id);
    }
}
