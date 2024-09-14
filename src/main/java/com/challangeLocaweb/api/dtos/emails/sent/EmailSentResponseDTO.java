package com.challangeLocaweb.api.dtos.emails.sent;

import com.challangeLocaweb.api.models.EmailSent;

import java.time.LocalDateTime;

public record EmailSentResponseDTO(

        Long id,
        Long userId,
        Long emailId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public EmailSentResponseDTO(EmailSent emailSent) {
        this(
                emailSent.getId(),
                emailSent.getUser().getId(),
                emailSent.getEmail().getId(),
                emailSent.getCreatedAt(),
                emailSent.getUpdatedAt()
        );
    }
}
