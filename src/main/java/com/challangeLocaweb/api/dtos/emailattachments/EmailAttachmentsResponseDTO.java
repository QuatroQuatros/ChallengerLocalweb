package com.challangeLocaweb.api.dtos.emailattachments;

import java.time.LocalDateTime;

import com.challangeLocaweb.api.models.EmailAttachments;

public record EmailAttachmentsResponseDTO(
    
    Long id,
    Long emailId,
    String fileName,
    String filePath,
    String mimeType,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public EmailAttachmentsResponseDTO(EmailAttachments attachment) {
        this(
            attachment.getId(),
            attachment.getEmail().getId(),
            attachment.getFileName(),
            attachment.getFilePath(),
            attachment.getMimeType(),
            attachment.getCreatedAt(),
            attachment.getUpdateAt()
        );
    }
}
