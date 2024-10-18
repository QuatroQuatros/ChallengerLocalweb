package com.challangeLocaweb.api.dtos.emails;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record EmailCreateDTO(

        @NotBlank(message = "{email.sender.notblank}")
        @Email(message = "{user.email.invalid}")
        String sender,

        @NotEmpty(message = "{email.receivers.notempty}")
        @Valid
        List<@Email(message = "{user.email.invalid}")String> receivers,

        @NotBlank(message = "{email.subject.notblank}")
        String subject,

        String contentHtml,

        String contentPlain,

        @NotNull(message = "{email.is_confidential.notnull}")
        Boolean isConfidential
) {}
