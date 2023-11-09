package com.odin.odinbff.repository;

import com.odin.odinbff.model.serviceorder.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {
}
