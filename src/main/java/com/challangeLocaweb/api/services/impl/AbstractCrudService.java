package com.challangeLocaweb.api.services.impl;

import com.challangeLocaweb.api.exceptions.ModelNotFoundException;
import com.challangeLocaweb.api.services.CRUDInterface;
//import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public abstract class AbstractCrudService<Entity, ID, CreateDTO, UpdateDTO, ResponseDTO> implements CRUDInterface<Entity, ID, CreateDTO, UpdateDTO, ResponseDTO> {

    protected abstract JpaRepository<Entity, ID> getRepository();

    protected abstract ResponseDTO toResponseDTO(Entity entity);

    protected abstract Entity toEntity(CreateDTO dto);

    protected abstract Entity updateEntity(Entity existingEntity, UpdateDTO updateDto);

    @Override
    public Page<ResponseDTO> getAll(Pageable paginacao) {
        return getRepository().findAll(paginacao).map(this::toResponseDTO);
    }

    @Override

    public ResponseDTO getById(ID id) throws ModelNotFoundException {
        Optional<Entity> entityOptional = getRepository().findById(id);
        if (entityOptional.isPresent()) {
            return toResponseDTO(entityOptional.get());
        } else {
            throw new ModelNotFoundException("error.entity.not.found");
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public ResponseDTO store(CreateDTO dto) {
        try{
            Entity entity = toEntity(dto);
            return toResponseDTO(getRepository().save(entity));
        } catch (DataIntegrityViolationException e) {
            throw e;
        }

    }

    @Transactional
    @Override
    public ResponseDTO update(ID id, UpdateDTO updateDto) throws ModelNotFoundException {
        Optional<Entity> entityOptional = getRepository().findById(id);
        if (entityOptional.isPresent()) {
            Entity updatedEntity = updateEntity(entityOptional.get(), updateDto);
            return toResponseDTO(getRepository().save(updatedEntity));

        } else {
            throw new ModelNotFoundException("error.entity.not.found");
        }
    }

    @Transactional
    @Override
    public void delete(ID id) {
        if (!getRepository().existsById(id)) {
            throw new ModelNotFoundException("error.entity.not.found");
        }
        getRepository().deleteById(id);
    }
}

