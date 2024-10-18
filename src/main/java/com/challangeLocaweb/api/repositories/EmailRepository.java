package com.challangeLocaweb.api.repositories;

import com.challangeLocaweb.api.models.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Long> {
    //@Query("SELECT e FROM Email e JOIN EmailsRecievers er ON e.id = er.idEmail WHERE er.reciever = :userEmail")
    //List<Email> findAllEmailsReceivedByUser(@Param("userEmail") String userEmail);

}
