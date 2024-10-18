package com.challangeLocaweb.api.services.impl;

import com.challangeLocaweb.api.dtos.emails.EmailCreateDTO;
import com.challangeLocaweb.api.dtos.emails.EmailResponseDTO;
import com.challangeLocaweb.api.dtos.emails.EmailUpdateDTO;
import com.challangeLocaweb.api.exceptions.DuplicateEntryException;
import com.challangeLocaweb.api.mails.EmailMessage;
import com.challangeLocaweb.api.models.Email;
import com.challangeLocaweb.api.models.EmailReceiver;
import com.challangeLocaweb.api.models.EmailSent;
import com.challangeLocaweb.api.models.User;
import com.challangeLocaweb.api.repositories.EmailReceiverRepository;
import com.challangeLocaweb.api.repositories.EmailRepository;
import com.challangeLocaweb.api.repositories.EmailSentRepository;
import com.challangeLocaweb.api.services.EmailService;
import com.challangeLocaweb.api.services.QueueService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import com.challangeLocaweb.api.helpers.AuthHelpers;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
public class EmailServiceImpl extends AbstractCrudService<Email, Long, EmailCreateDTO, EmailUpdateDTO, EmailResponseDTO> implements EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private EmailSentRepository emailSentRepository;

    @Autowired
    private EmailReceiverRepository emailReceiverRepository;


    @Autowired
    private QueueService queueService;

    @Override
    protected JpaRepository<Email, Long> getRepository() {
        return emailRepository;
    }

    @Override
    protected EmailResponseDTO toResponseDTO(Email email) {
        return new EmailResponseDTO(email);
    }

    @Autowired
    private AuthHelpers authHelpers;


    @Override
    protected Email toEntity(EmailCreateDTO emailData) {
        Email email = new Email();
        BeanUtils.copyProperties(emailData, email);

        return email;
    }

    @Override
    protected Email updateEntity(Email email, EmailUpdateDTO emailData) {
        email.setIsConfidential(emailData.isConfidential());

        return email;
    }

    @Override
    @Transactional
    public EmailResponseDTO store(EmailCreateDTO emailData) {
        try {
            Email email = new Email();
            BeanUtils.copyProperties(emailData, email);

            Email emailSaved = emailRepository.save(email);

            User user = authHelpers.getUser();

            EmailMessage emailMessage = new EmailMessage(email.getSender(), email.getSubject(), email.getContentPlain());
            queueService.queueEmail(emailMessage);

            EmailSent emailSent = new EmailSent();
            emailSent.setEmail(emailSaved);
            emailSent.setUser(user);
            emailSentRepository.save(emailSent);

            for (String receiver : emailData.receivers()) {
                EmailReceiver emailReceiver = new EmailReceiver();
                emailReceiver.setEmail(emailSaved);
                emailReceiver.setReceiver(receiver);
                emailReceiver.setIsCc(false);
                emailReceiver.setIsCco(false);
                emailReceiverRepository.save(emailReceiver);
            }

            return new EmailResponseDTO(emailSaved);

        } catch (DataIntegrityViolationException e) {
            if (e.getRootCause() instanceof SQLIntegrityConstraintViolationException) {
                throw new DuplicateEntryException("error.email.duplicate", e);
            }
            throw e;
        }
    }


}
