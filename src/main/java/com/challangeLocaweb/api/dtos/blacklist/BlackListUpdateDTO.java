package com.challangeLocaweb.api.dtos.blacklist;

import jakarta.validation.constraints.NotNull;

public record BlackListUpdateDTO(
    @NotNull
    String reason,

    @NotNull
    boolean status
) {

}
