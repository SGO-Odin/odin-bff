package com.odin.odinbff.controller.purveyor;


import com.odin.odinbff.controller.Api;
import com.odin.odinbff.model.Purveyor;
import com.odin.odinbff.repository.PurveyorReposioty;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Api.Purveyor.PURVEYOR_RESOURCE)
@Transactional
public class PurveyorController {

    @Autowired
    private PurveyorReposioty purveyorReposioty;


    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody final PurveyorFormRequest purveyorFormRequest){
        Purveyor newPurveyor = purveyorFormRequest.toModel();
        purveyorReposioty.save(newPurveyor);

        final URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(Api.Purveyor.PURVEYOR_READ_BY_ID)
                .buildAndExpand(newPurveyor.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(Api.PATH_PARAM_ID)
    public Optional<PurveyorResponse> read(@PathVariable final Long id){
        return Optional.of(purveyorReposioty.getReferenceById(id)).map(PurveyorResponse::new);
    }


}