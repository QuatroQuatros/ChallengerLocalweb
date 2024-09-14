package com.challangeLocaweb.api.repositories;

import com.challangeLocaweb.api.models.EmailSent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailSentRepository extends JpaRepository<EmailSent, Long> {
}
