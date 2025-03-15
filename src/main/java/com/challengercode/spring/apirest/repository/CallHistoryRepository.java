package com.challengercode.spring.apirest.repository;

import com.challengercode.spring.apirest.model.CallHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallHistoryRepository extends JpaRepository<CallHistory, Long> {
}
