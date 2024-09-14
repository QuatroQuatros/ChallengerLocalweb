package com.challangeLocaweb.api.dtos.blacklist;

import com.challangeLocaweb.api.models.Blacklist;

import java.time.LocalDateTime;

public record BlacklistResponseDTO(
        Long id,
        String ipAddress,
        String domain,
        String reason,
        Boolean status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public BlacklistResponseDTO(Blacklist blacklist) {
        this(
                blacklist.getId(),
                blacklist.getIpAddress(),
                blacklist.getDomain(),
                blacklist.getReason(),
                blacklist.getStatus(),
                blacklist.getCreatedAt(),
                blacklist.getUpdatedAt()
        );
    }
}
