package com.challangeLocaweb.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challangeLocaweb.api.models.SystemLabels;

@Repository
public interface SystemLabelsRepository extends JpaRepository<SystemLabels, Long> {
    
}
