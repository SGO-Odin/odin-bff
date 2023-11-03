package com.odin.odinbff.controller.client;

import com.odin.odinbff.controller.Api;
import com.odin.odinbff.model.Client;
import com.odin.odinbff.repository.ClientRepository;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Api.CLIENT)
public class ClientController {

    @Autowired
    private final ClientRepository clientRepository;

    public ClientController(@Autowired ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostMapping
    @Transient
    public ClientResponse save(@Valid @RequestBody ClientFormRequest clientFormRequest) {
        Client newClient = clientFormRequest.toModel();
        clientRepository.save(newClient);
        return new ClientResponse(newClient);
    }

    @GetMapping
    public List<ClientResponse> getAll() {
        return clientRepository.findAll().stream().map(ClientResponse::new).collect(Collectors.toList());
    }
}
