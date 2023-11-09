package com.odin.odinbff.repository;

import com.odin.odinbff.model.address.City;
import com.odin.odinbff.model.address.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByStateAndName(final State stateAcronym, final String name);
}
