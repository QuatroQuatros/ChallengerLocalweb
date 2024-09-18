package com.challangeLocaweb.api.services.impl;

import com.challangeLocaweb.api.dtos.user.UserCreateDTO;
import com.challangeLocaweb.api.dtos.user.UserResponseDTO;
import com.challangeLocaweb.api.dtos.user.UserUpdateDTO;
import com.challangeLocaweb.api.events.UserCreatedEvent;
import com.challangeLocaweb.api.exceptions.DuplicateEntryException;
import com.challangeLocaweb.api.models.User;
import com.challangeLocaweb.api.models.UserPreferences;
import com.challangeLocaweb.api.repositories.UserPreferencesRepository;
import com.challangeLocaweb.api.repositories.UserRepository;
import com.challangeLocaweb.api.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@Service
public class UserServiceImpl extends AbstractCrudService<User, Long, UserCreateDTO, UserUpdateDTO, UserResponseDTO> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPreferencesRepository userPreferencesRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

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
    @Transactional
    public UserResponseDTO store(UserCreateDTO userData) {
        try {
            if (userData.password() == null) {
                throw new IllegalArgumentException("error.password.required");
            }
            String passwordHash = new BCryptPasswordEncoder().encode(userData.password());

            User user = new User();
            BeanUtils.copyProperties(userData, user);
            user.setPassword(passwordHash);

            if(userData.photo() == null) {
                user.setPhoto("https://ui-avatars.com/api/?background=random&name=" + userData.name());
            }

            user = userRepository.save(user);

            UserPreferences preferences = new UserPreferences();
            if (userData.preferences() != null) {
                preferences.setTheme(userData.preferences().theme());
                preferences.setLanguage(userData.preferences().language());
            } else {
                preferences.setTheme("system_default");
                preferences.setLanguage("system_default");
            }

            preferences.setUser(user);
            UserPreferences preference = userPreferencesRepository.save(preferences);
            user.setPreferences(preference);

            eventPublisher.publishEvent(new UserCreatedEvent(user));

            return new UserResponseDTO(user);

        } catch (DataIntegrityViolationException  e) {
            if (e.getRootCause() instanceof SQLIntegrityConstraintViolationException) {
                throw new DuplicateEntryException("error.email.duplicate", e);
            }
            throw e;
        }
    }

    @Override
    @Transactional
    public UserResponseDTO update(Long userId, UserUpdateDTO userData) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("error.user.not_found"));

        if (userData.name() != null && !userData.name().isEmpty()) {
            user.setName(userData.name());
        }

        if (userData.email() != null && !userData.email().isEmpty()) {
            user.setEmail(userData.email());
        }

        if (userData.preferences() != null) {
            UserPreferences preferences = user.getPreferences();

            if (preferences == null) {
                preferences = new UserPreferences();
                preferences.setUser(user);
            }

            preferences.setTheme(userData.preferences().theme());
            preferences.setLanguage(userData.preferences().language());

            preferences = userPreferencesRepository.save(preferences);

            user.setPreferences(preferences);
        }

        userRepository.save(user);

        return new UserResponseDTO(user);
    }

    @Override
    public User findByEmail(String email) {
        return (User)userRepository.findByEmail(email);
    }
}


