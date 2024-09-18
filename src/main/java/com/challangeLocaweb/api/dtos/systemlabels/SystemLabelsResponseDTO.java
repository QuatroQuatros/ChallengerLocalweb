package com.challangeLocaweb.api.dtos.systemlabels;

import java.time.LocalDateTime;

import com.challangeLocaweb.api.models.SystemLabels;

public record SystemLabelsResponseDTO(
    Long id,
    String labelName,
    LocalDateTime updatedAt,
    LocalDateTime createdAt
) {
    public SystemLabelsResponseDTO(SystemLabels systemLabels) {
        this(
            systemLabels.getId(),
            systemLabels.getLabelName(),
            systemLabels.getUpdatedAt(),
            systemLabels.getCreatedAt())
        ;

    }
}
