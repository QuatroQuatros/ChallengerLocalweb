package com.challangeLocaweb.api.dtos.emails.sent;

import com.challangeLocaweb.api.models.EmailSent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EmailSentUpdateDTO(

        @NotNull(message = "{emails_user_labels.user_id.notnull}")
        Long userId,

        @NotNull(message = "{emails_user_labels.email_id.notnull}")
        Long emailId
) {
}
