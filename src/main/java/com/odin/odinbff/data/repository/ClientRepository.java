package com.odin.odinbff.data.repository;

import com.odin.odinbff.data.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
