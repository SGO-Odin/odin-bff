package com.odin.odinbff.repository;

import com.odin.odinbff.model.purveyor.Purveyor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PurveyorReposioty extends JpaRepository<Purveyor, Long> {
    Set<Purveyor> findAllByIsActive(final Boolean isActive);
}
