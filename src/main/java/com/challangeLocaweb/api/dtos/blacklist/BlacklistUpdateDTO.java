package com.challangeLocaweb.api.dtos.blacklist;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;

public record BlacklistUpdateDTO(

        @NotBlank(message = "{blacklist.ip.notblank}")
        @Pattern(regexp = "^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$", message = "{blacklist.ip.invalid}")
        String ipAddress,

        @NotBlank(message = "{blacklist.domain.notblank}")
        String domain,

        @NotBlank(message = "{blacklist.reason.notblank}")
        String reason,

        Boolean status
) {}
