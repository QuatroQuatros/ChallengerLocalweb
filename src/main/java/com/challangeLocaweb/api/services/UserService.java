package com.challangeLocaweb.api.services;

import com.challangeLocaweb.api.dtos.user.UserCreateDTO;
import com.challangeLocaweb.api.dtos.user.UserResponseDTO;
import com.challangeLocaweb.api.dtos.user.UserUpdateDTO;
import com.challangeLocaweb.api.models.User;

public interface UserService extends CRUDInterface<User, Long, UserCreateDTO, UserUpdateDTO, UserResponseDTO> {


}

