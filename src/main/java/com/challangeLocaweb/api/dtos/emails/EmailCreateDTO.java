package com.challangeLocaweb.api.dtos.emails;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmailCreateDTO(

        @NotBlank(message = "{email.sender.notblank}")
        @Email(message = "{user.email.invalid}")
        String sender,

        @NotBlank(message = "{email.subject.notblank}")
        String subject,

        String contentHtml,

        String contentPlain,

        @NotNull(message = "{email.is_confidential.notnull}")
        Boolean isConfidential
) {}
