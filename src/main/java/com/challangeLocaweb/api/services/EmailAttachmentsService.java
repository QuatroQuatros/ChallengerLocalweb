package com.challangeLocaweb.api.services;

import com.challangeLocaweb.api.dtos.emailattachments.EmailAttachmentsCreateDTO;
import com.challangeLocaweb.api.dtos.emailattachments.EmailAttachmentsResponseDTO;
import com.challangeLocaweb.api.dtos.emailattachments.EmailAttachmentsUpdateDTO;
import com.challangeLocaweb.api.models.EmailAttachments;

public interface EmailAttachmentsService extends CRUDInterface<EmailAttachments, Long, EmailAttachmentsCreateDTO, EmailAttachmentsUpdateDTO, EmailAttachmentsResponseDTO> {
    
    EmailAttachmentsResponseDTO getByEmail();

}
