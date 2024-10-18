package com.challangeLocaweb.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.challangeLocaweb.api.models.BlackList;

@Repository
public interface BlackListRepository extends JpaRepository<BlackList, Long> {
    
}
