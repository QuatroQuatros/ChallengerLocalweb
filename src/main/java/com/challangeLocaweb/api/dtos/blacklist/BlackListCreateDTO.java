package com.challangeLocaweb.api.dtos.blacklist;

import jakarta.validation.constraints.NotBlank;

public record BlackListCreateDTO(
    @NotBlank
    String ipAddress,

    @NotBlank
    String reason,

    @NotBlank
    boolean status
) {    
}
