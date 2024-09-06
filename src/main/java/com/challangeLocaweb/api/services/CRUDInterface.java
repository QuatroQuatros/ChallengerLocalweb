package com.challangeLocaweb.api.services;

import com.challangeLocaweb.api.exceptions.ModelNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CRUDInterface<Entity, ID, CreateDTO, UpdateDTO, ResponseDTO> {

    Page<ResponseDTO> getAll(Pageable paginacao);

    ResponseDTO getById(ID id) throws ModelNotFoundException;

    ResponseDTO store(CreateDTO dto);
    ResponseDTO update(ID id, UpdateDTO dto) throws ModelNotFoundException;
    void delete(ID id) throws ModelNotFoundException;
}

