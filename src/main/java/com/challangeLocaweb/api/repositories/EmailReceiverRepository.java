package com.challangeLocaweb.api.repositories;

import com.challangeLocaweb.api.models.EmailReceiver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailReceiverRepository extends JpaRepository<EmailReceiver, Long> {
}
