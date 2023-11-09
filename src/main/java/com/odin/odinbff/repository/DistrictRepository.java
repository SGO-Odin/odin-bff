package com.odin.odinbff.repository;

import com.odin.odinbff.model.address.City;
import com.odin.odinbff.model.address.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DistrictRepository extends JpaRepository<District, Long> {
    Optional<District> findByCityAndNameIgnoreCase(final City city, final String name);
}
