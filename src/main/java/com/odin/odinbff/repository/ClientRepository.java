package com.odin.odinbff.repository;

import com.odin.odinbff.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
