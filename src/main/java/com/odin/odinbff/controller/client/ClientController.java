package com.odin.odinbff.controller.client;

import com.odin.odinbff.controller.Api;
import com.odin.odinbff.model.client.Client;
import com.odin.odinbff.repository.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Api.Client.CLIENT_RESOURCE)
public class ClientController {

    private final ClientRepository clientRepository;

    private final PublicPlaceRepository publicPlaceRepository;

    private final DistrictRepository districtRepository;

    private final CityRepository cityRepository;

    private final StateRepository stateRepository;

    public ClientController(@Autowired ClientRepository clientRepository,
                            @Autowired final PublicPlaceRepository publicPlaceRepository,
                            @Autowired final DistrictRepository districtRepository,
                            @Autowired final CityRepository cityRepository,
                            @Autowired final StateRepository stateRepository) {
        this.clientRepository = clientRepository;
        this.publicPlaceRepository = publicPlaceRepository;
        this.districtRepository = districtRepository;
        this.cityRepository = cityRepository;
        this.stateRepository = stateRepository;
    }

    @GetMapping(Api.PATH_PARAM_ID)
    public ResponseEntity<ClientResponse> get(@PathVariable Long id) {
        Optional<Client> possibleClient =  clientRepository.findById(id);

        return possibleClient.map(client -> ResponseEntity.ok(new ClientResponse(client)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> save(@Valid @RequestBody final ClientFormRequest clientFormRequest,
                               @Autowired final UriComponentsBuilder uriBuilder) {
        Client newClient = clientFormRequest.toModel(publicPlaceRepository, districtRepository, cityRepository, stateRepository);
        clientRepository.save(newClient);

        final URI location = uriBuilder
                .path(Api.Client.CLIENT_READ_BY_ID)
                .buildAndExpand(newClient.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public List<ClientResponse> getAll() {
        return clientRepository.findAll().stream().map(ClientResponse::new).collect(Collectors.toList());
    }
}
