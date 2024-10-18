package com.challangeLocaweb.api.dtos.attachments;

import jakarta.validation.constraints.NotBlank;

public record AttachnmentCreateDTO(
    @NotBlank(message = "{attachment.file_name.notblank}")
    String fileName,

    @NotBlank(message = "{attachment.file_path.notblank}")
    String filePath,

    @NotBlank(message = "{attachment.mime_type.notblank}")
    String mimeType
) {}
