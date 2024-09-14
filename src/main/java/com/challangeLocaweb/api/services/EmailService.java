package com.challangeLocaweb.api.services;

import com.challangeLocaweb.api.dtos.emails.EmailCreateDTO;
import com.challangeLocaweb.api.dtos.emails.EmailResponseDTO;
import com.challangeLocaweb.api.dtos.emails.EmailUpdateDTO;
import com.challangeLocaweb.api.models.Email;

public interface EmailService extends CRUDInterface<Email, Long, EmailCreateDTO, EmailUpdateDTO, EmailResponseDTO>{
}
