package com.challangeLocaweb.api.dtos.user;

import com.challangeLocaweb.api.dtos.BaseResponseDTO;
import com.challangeLocaweb.api.models.User;

import java.time.LocalDateTime;

public record UserResponseDTO(
        Long id,
        String name,
        String email,

        LocalDateTime created_at,

        LocalDateTime  update_at
) {
    public UserResponseDTO(User user){
        this(
                user.getUserid(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public BaseResponseDTO<UserResponseDTO> toResponseDto() {
        return new BaseResponseDTO<>( "fetch users successfuly", this);
    }
}
