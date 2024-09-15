package com.challangeLocaweb.api.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.challangeLocaweb.api.models.User;
import com.challangeLocaweb.api.models.UserPreferences;

public interface UserPreferencesRepository extends JpaRepository<UserPreferences, Long> {

    Optional<UserPreferences> findByUser(User user);

}
