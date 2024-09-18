package com.challangeLocaweb.api.services;

import com.challangeLocaweb.api.dtos.user.UserCreateDTO;
import com.challangeLocaweb.api.dtos.user.UserResponseDTO;
import com.challangeLocaweb.api.dtos.user.UserUpdateDTO;
import com.challangeLocaweb.api.models.User;

import java.util.Optional;

public interface UserService extends CRUDInterface<User, Long, UserCreateDTO, UserUpdateDTO, UserResponseDTO> {
    User findByEmail(String email);

}

