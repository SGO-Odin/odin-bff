package com.odin.odinbff.controller.brand;

import com.odin.odinbff.controller.Api;
import com.odin.odinbff.model.product.Brand;
import com.odin.odinbff.repository.BrandRepository;
import com.odin.odinbff.service.ActivableService;
import com.odin.odinbff.service.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Api.Brand.BRAND_RESOURCE)
public final class BrandController {

    private final BrandRepository brandRepository;

    private final BrandService brandService;

    private final ActivableService activableService;

    public BrandController(@Autowired final BrandRepository brandRepository,
                           @Autowired final BrandService brandService,
                           @Autowired final ActivableService activableService) {
        this.brandRepository = brandRepository;
        this.brandService = brandService;
        this.activableService = activableService;
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
            try {
                activableService.inactivate(o);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping(Api.PATH_PARAM_ID_ACTIVATE)
    public ResponseEntity<?> activate(@PathVariable final Long id) {
        return brandRepository.findById(id).map(o -> {
            try {
                activableService.activate(o);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody BrandFormRequest request, @Autowired UriComponentsBuilder uriComponentsBuilder) throws NoSuchFieldException, IllegalAccessException {
        final Brand brand = request.toModel();
        brandService.create(brand);
        return ResponseEntity.created(
                uriComponentsBuilder
                        .path(Api.Brand.BRAND_READ_BY_ID)
                        .buildAndExpand(brand.getId())
                        .toUri()
        ).build();
    }

    @PutMapping(Api.PATH_PARAM_ID)
    public void update(@PathVariable Long id, @Valid @RequestBody BrandFormRequest formRequest)
            throws NoSuchFieldException, IllegalAccessException {
        brandService.update(id, formRequest.toModel(id));
    }
}
