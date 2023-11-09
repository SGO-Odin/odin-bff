package com.odin.odinbff.repository;

import com.odin.odinbff.model.audit.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
