package com.challangeLocaweb.api.dtos.emails;

import com.challangeLocaweb.api.models.Email;

import java.time.LocalDateTime;

public record EmailResponseDTO(
        Long id,
        String sender,
        String subject,
        String contentHtml,
        String contentPlain,
        Boolean isConfidential,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public EmailResponseDTO(Email email) {
        this(
                email.getId(),
                email.getSender(),
                email.getSubject(),
                email.getContentHtml(),
                email.getContentPlain(),
                email.getIsConfidential(),
                email.getCreatedAt(),
                email.getUpdatedAt()
        );
    }
}
