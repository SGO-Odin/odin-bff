package com.odin.odinbff.controller.brand;

import com.odin.odinbff.model.Brand;
import com.odin.odinbff.repository.BrandRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

    private final BrandRepository brandRepository;

    public BrandController(@Autowired final BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @GetMapping
    public List<BrandResponse> getList() {
        return brandRepository.findAll().stream()
                .map(BrandResponse::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public BrandResponse save(@Valid @RequestBody BrandFormRequest request) {
        Brand brand = request.toModel();
        brandRepository.save(brand);
        return new BrandResponse(brand);
    }
}
