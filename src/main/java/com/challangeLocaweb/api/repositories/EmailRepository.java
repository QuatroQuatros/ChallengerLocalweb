package com.challangeLocaweb.api.repositories;

import com.challangeLocaweb.api.models.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
