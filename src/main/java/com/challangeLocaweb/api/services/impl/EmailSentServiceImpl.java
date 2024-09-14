package com.challangeLocaweb.api.services.impl;

import com.challangeLocaweb.api.dtos.emails.sent.EmailSentCreateDTO;
import com.challangeLocaweb.api.dtos.emails.sent.EmailSentResponseDTO;
import com.challangeLocaweb.api.dtos.emails.sent.EmailSentUpdateDTO;
import com.challangeLocaweb.api.models.EmailSent;
import com.challangeLocaweb.api.repositories.EmailRepository;
import com.challangeLocaweb.api.repositories.EmailSentRepository;
import com.challangeLocaweb.api.services.EmailSentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public class EmailSentServiceImpl  extends AbstractCrudService<EmailSent, Long, EmailSentCreateDTO, EmailSentUpdateDTO, EmailSentResponseDTO> implements EmailSentService {
    @Autowired
    private EmailSentRepository emailSentRepository;

    @Autowired
    private EmailRepository emailRepository;

    @Override
    protected JpaRepository<EmailSent, Long> getRepository() {
        return emailSentRepository;
    }

    @Override
    protected EmailSentResponseDTO toResponseDTO(EmailSent emailSent) {
        return new EmailSentResponseDTO(emailSent);
    }


    @Override
    protected EmailSent toEntity(EmailSentCreateDTO emailData) {
        EmailSent emailSent = new EmailSent();
        BeanUtils.copyProperties(emailData, emailSent);

        return emailSent;
    }

    @Override
    protected EmailSent updateEntity(EmailSent emailSent, EmailSentUpdateDTO emailData) {
        //emailSent.setUserId(emailData.userId());
        //emailSent.setEmailId(emailData.emailId());

        //bypass

        return emailSent;
    }

}
