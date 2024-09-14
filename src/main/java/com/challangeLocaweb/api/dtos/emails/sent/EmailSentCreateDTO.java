package com.challangeLocaweb.api.dtos.emails.sent;

import jakarta.validation.constraints.NotNull;

public record EmailSentCreateDTO(

        @NotNull(message = "{emails_user_labels.user_id.notnull}")
        Long userId,

        @NotNull(message = "{emails_user_labels.email_id.notnull}")
        Long emailId
) {}

