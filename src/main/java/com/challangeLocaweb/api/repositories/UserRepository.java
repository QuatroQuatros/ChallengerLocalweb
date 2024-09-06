package com.challangeLocaweb.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.challangeLocaweb.api.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public UserDetails findByEmail(String email);
}
