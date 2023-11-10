package com.odin.odinbff.controller.address;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.odin.odinbff.model.address.District;
import com.odin.odinbff.repository.PublicPlaceRepository;

@RestController
public class AddressController {
    private final PublicPlaceRepository publicPlaceRepository;

    public AddressController(@Autowired final PublicPlaceRepository publicPlaceRepository) {
        this.publicPlaceRepository = publicPlaceRepository;
    }

    @GetMapping("/list")
    public List<PublicPlaceResponse> getList(@RequestParam String name, @RequestParam District district) {
        return publicPlaceRepository.findByNameAndDistrict(name, district).stream()
                .map(PublicPlaceResponse::new)
                .collect(Collectors.toList());
    }
}
