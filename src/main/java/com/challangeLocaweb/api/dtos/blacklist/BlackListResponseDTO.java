package com.challangeLocaweb.api.dtos.blacklist;

import java.time.LocalDateTime;

import com.challangeLocaweb.api.dtos.BaseResponseDTO;
import com.challangeLocaweb.api.models.BlackList;

public record BlackListResponseDTO(
    Long id,
    String ipAddress,
    String reason,
    LocalDateTime created_at,
    LocalDateTime updated_at
) {
    public BlackListResponseDTO(BlackList blackList) {
        this(
            blackList.getBlackListId(),
            blackList.getIpAddress(),
            blackList.getReason(),
            blackList.getCreatedAt(),
            blackList.getUpdateAt()
        );
    }

    public BaseResponseDTO<BlackListResponseDTO> toResponseDTO(){
        return new BaseResponseDTO<>("fetch blacklist successfuly",this);
    }

}
