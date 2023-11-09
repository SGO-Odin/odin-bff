package com.odin.odinbff.repository;

import com.odin.odinbff.model.address.District;
import com.odin.odinbff.model.address.PublicPlace;
import com.odin.odinbff.model.address.ZipCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublicPlaceRepository extends JpaRepository<PublicPlace, Long> {
    PublicPlace findByZipCode(final ZipCode zipCode);

    Optional<PublicPlace> findByNameAndDistrict(String name, District district);
}
