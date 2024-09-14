package com.challangeLocaweb.api.dtos.emails;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmailUpdateDTO(


        @NotNull(message = "{email.is_confidential.notnull}")
        Boolean isConfidential
) {}
