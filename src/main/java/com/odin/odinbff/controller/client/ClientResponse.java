package com.odin.odinbff.controller.client;

import com.odin.odinbff.model.*;

import java.util.Set;
import java.util.stream.Collectors;

public final class ClientResponse {

    private final Client client;

    public ClientResponse(Client client) {
        this.client = client;
    }

    public Long getId() {
        return client.getId();
    }

    public String getFirstName() {
        return client.getFirstName();
    }

    public String getLastName() {
        return client.getLastName();
    }

    public String getCpf() {
        return client.getCpf().getNumbers();
    }

    public String getRg() {
        return client.getRg();
    }

    public String getAddress() {
        return client.getAddress().getPublicPlace().getName();
    }

    public Set<String> getEmails() {
        return client.getEmails().stream().map(ce -> ce.getEmail().getEmail()).collect(Collectors.toSet());
    }

    public Set<String> getPhones() {
        return client.getPhones().stream().map(cp -> cp.getPhone().getPrettyNumber()).collect(Collectors.toSet());
    }
}
