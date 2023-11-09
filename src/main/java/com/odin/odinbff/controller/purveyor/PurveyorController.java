package com.odin.odinbff.controller.purveyor;


import com.odin.odinbff.controller.Api;
import com.odin.odinbff.model.purveyor.Purveyor;
import com.odin.odinbff.repository.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Api.Purveyor.PURVEYOR_RESOURCE)
@Transactional
public class PurveyorController {

    @Autowired
    private final PurveyorReposioty purveyorReposioty;

    private final PublicPlaceRepository publicPlaceRepository;

    private final DistrictRepository districtRepository;

    private final CityRepository cityRepository;

    private final StateRepository stateRepository;

    public PurveyorController(@Autowired final PurveyorReposioty purveyorReposioty,
                              @Autowired final PublicPlaceRepository publicPlaceRepository,
                              @Autowired final DistrictRepository districtRepository,
                              @Autowired final CityRepository cityRepository,
                              @Autowired final StateRepository stateRepository) {
        this.purveyorReposioty = purveyorReposioty;
        this.publicPlaceRepository = publicPlaceRepository;
        this.districtRepository = districtRepository;
        this.cityRepository = cityRepository;
        this.stateRepository = stateRepository;
    }


    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody final PurveyorFormRequest purveyorFormRequest){
        Purveyor newPurveyor = purveyorFormRequest.toModel(publicPlaceRepository, districtRepository, cityRepository, stateRepository);
        purveyorReposioty.save(newPurveyor);

        final URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(Api.PATH_PARAM_ID)
                .buildAndExpand(newPurveyor.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(Api.PATH_PARAM_ID)
    public Optional<PurveyorResponse> get(@PathVariable final Long id){
        return Optional.of(purveyorReposioty.getReferenceById(id)).map(PurveyorResponse::new);
    }

    @GetMapping
    public List<PurveyorResponse> getAll() {
        return purveyorReposioty.findAll().stream().map(PurveyorResponse::new).collect(Collectors.toList());
    }
}
