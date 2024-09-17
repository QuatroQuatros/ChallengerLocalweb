package com.challangeLocaweb.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challangeLocaweb.api.models.Email;
import com.challangeLocaweb.api.models.EmailAttachments;
import java.util.List;


@Repository
public interface EmailAttachmentsRepository extends JpaRepository<EmailAttachments, Long> {

    public List<EmailAttachments> findByEmail(Email email);

}
