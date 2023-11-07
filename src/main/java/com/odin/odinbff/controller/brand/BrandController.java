package com.odin.odinbff.controller.brand;

import com.odin.odinbff.controller.Api;
import com.odin.odinbff.model.Brand;
import com.odin.odinbff.repository.BrandRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Api.Brand.BRAND_RESOURCE)
public final class BrandController {

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

    @GetMapping(Api.PATH_PARAM_ID)
    public ResponseEntity<BrandResponse> get(@PathVariable final Long id) {
        return brandRepository.findById(id)
                .map(brand -> ResponseEntity.ok(new BrandResponse(brand)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping(Api.PATH_PARAM_ID_INACTIVATE)
    public ResponseEntity<?> inactivate(@PathVariable final Long id) {
        return brandRepository.findById(id).map(o -> {
            o.inactivate();
            brandRepository.save(o);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping(Api.PATH_PARAM_ID_ACTIVATE)
    public ResponseEntity<?> activate(@PathVariable final Long id) {
        return brandRepository.findById(id).map(o -> {
            o.activate();
            brandRepository.save(o);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody BrandFormRequest request, @Autowired UriComponentsBuilder uriComponentsBuilder) {
        Brand brand = request.toModel();
        brandRepository.save(brand);
        return ResponseEntity.created(
                uriComponentsBuilder
                        .path(Api.Brand.BRAND_READ_BY_ID)
                        .buildAndExpand(brand.getId())
                        .toUri()
        ).build();
    }
}
