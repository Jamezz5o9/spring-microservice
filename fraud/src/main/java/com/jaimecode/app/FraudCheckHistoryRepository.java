package com.jaimecode.app;

import org.springframework.data.jpa.repository.JpaRepository;


public interface FraudCheckHistoryRepository
        extends JpaRepository<FraudCheckHistory, Integer> {
}
