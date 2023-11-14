package com.odin.odinbff.repository;

import com.odin.odinbff.model.serviceorder.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {
    @Query("select coalesce(max(number), 1) from ServiceOrder")
    Long getNextNumber();

    List<ServiceOrder> findByStatus(final ServiceOrder.StatusType status);

    List<ServiceOrder> findByStatusAndClient_id(final ServiceOrder.StatusType status, final Long clientId);
}
