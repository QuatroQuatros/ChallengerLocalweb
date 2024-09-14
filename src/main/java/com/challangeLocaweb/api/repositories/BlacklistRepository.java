package com.challangeLocaweb.api.repositories;

import com.challangeLocaweb.api.models.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistRepository extends JpaRepository<Blacklist, Long> {
}
