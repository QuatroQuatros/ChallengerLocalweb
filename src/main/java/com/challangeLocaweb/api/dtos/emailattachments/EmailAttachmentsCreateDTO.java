package com.challangeLocaweb.api.dtos.emailattachments;

import jakarta.validation.constraints.NotBlank;

public record EmailAttachmentsCreateDTO(

    @NotBlank
    Long emailId,

    @NotBlank(message = "")
    String fileName,

    @NotBlank(message = "")
    String filePath,

    @NotBlank(message = "")
    String mimeType
    
) {
}
