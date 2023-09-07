package com.odin.odinbff.controller.client;

import com.odin.odinbff.data.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping
    public void create(CreateClientRequest clientRequest) {
        clientRepository.save(clientRequest.toClient());
    }
}
