package com.challangeLocaweb.api.dtos;

public record BaseResponseDTO<T>(
        String message,
        T data
){

    public BaseResponseDTO(String message) {
        this(message, null);
    }

}
