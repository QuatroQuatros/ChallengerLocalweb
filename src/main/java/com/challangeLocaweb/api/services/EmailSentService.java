package com.challangeLocaweb.api.services;

import com.challangeLocaweb.api.dtos.emails.sent.EmailSentCreateDTO;
import com.challangeLocaweb.api.dtos.emails.sent.EmailSentResponseDTO;
import com.challangeLocaweb.api.dtos.emails.sent.EmailSentUpdateDTO;
import com.challangeLocaweb.api.models.EmailSent;

public interface EmailSentService extends CRUDInterface<EmailSent, Long, EmailSentCreateDTO, EmailSentUpdateDTO, EmailSentResponseDTO> {
}
