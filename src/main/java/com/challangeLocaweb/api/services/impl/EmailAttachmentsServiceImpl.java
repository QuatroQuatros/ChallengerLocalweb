package com.challangeLocaweb.api.services.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challangeLocaweb.api.dtos.emailattachments.EmailAttachmentsCreateDTO;
import com.challangeLocaweb.api.dtos.emailattachments.EmailAttachmentsResponseDTO;
import com.challangeLocaweb.api.dtos.emailattachments.EmailAttachmentsUpdateDTO;
import com.challangeLocaweb.api.models.EmailAttachments;
import com.challangeLocaweb.api.services.EmailAttachmentsService;

public class EmailAttachmentsServiceImpl extends AbstractCrudService<EmailAttachments, Long, EmailAttachmentsCreateDTO, EmailAttachmentsUpdateDTO, EmailAttachmentsResponseDTO> implements EmailAttachmentsService {

    @Override
    public EmailAttachmentsResponseDTO getByEmail() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByEmail'");
    }

    @Override
    protected JpaRepository<EmailAttachments, Long> getRepository() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRepository'");
    }

    @Override
    protected EmailAttachmentsResponseDTO toResponseDTO(EmailAttachments entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toResponseDTO'");
    }

    @Override
    protected EmailAttachments toEntity(EmailAttachmentsCreateDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toEntity'");
    }

    @Override
    protected EmailAttachments updateEntity(EmailAttachments existingEntity, EmailAttachmentsUpdateDTO updateDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateEntity'");
    }

}
