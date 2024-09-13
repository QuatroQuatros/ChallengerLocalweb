package com.challangeLocaweb.api.services.impl;

import com.challangeLocaweb.api.dtos.user.UserCreateDTO;
import com.challangeLocaweb.api.dtos.user.UserResponseDTO;
import com.challangeLocaweb.api.dtos.user.UserUpdateDTO;
import com.challangeLocaweb.api.exceptions.DuplicateEntryException;
import com.challangeLocaweb.api.models.User;
import com.challangeLocaweb.api.repositories.UserRepository;
import com.challangeLocaweb.api.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
public class UserServiceImpl extends AbstractCrudService<User, Long, UserCreateDTO, UserUpdateDTO, UserResponseDTO> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected JpaRepository<User, Long> getRepository() {
        return userRepository;
    }

    @Override
    protected UserResponseDTO toResponseDTO(User usuario) {
        return new UserResponseDTO(usuario);
    }


    @Override
    protected User toEntity(UserCreateDTO usuarioCadastroDto) {
        User user = new User();
        BeanUtils.copyProperties(usuarioCadastroDto, user);

        return user;
    }

    @Override
    protected User updateEntity(User user, UserUpdateDTO userData) {
        user.setName(userData.name());
        user.setEmail(userData.email());
        return user;
    }

    @Override
    public UserResponseDTO store(UserCreateDTO userData) {
        try {
            String passwordHash = new BCryptPasswordEncoder().encode(userData.password());

            User user = new User();
            BeanUtils.copyProperties(userData, user);
            user.setPassword(passwordHash);

            if(userData.photo() == null) {
                user.setPhoto("https://ui-avatars.com/api/?background=random&name=" + userData.name());
            }

            return new UserResponseDTO(userRepository.save(user));

        } catch (DataIntegrityViolationException  e) {
            if (e.getRootCause() instanceof SQLIntegrityConstraintViolationException) {
                throw new DuplicateEntryException("error.email.duplicate", e);
            }
            throw e;
        }
    }

}
