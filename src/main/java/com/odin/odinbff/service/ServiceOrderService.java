package com.odin.odinbff.service;

import com.odin.odinbff.controller.Api;
import com.odin.odinbff.model.audit.History;
import com.odin.odinbff.model.serviceorder.ServiceOrder;
import com.odin.odinbff.model.serviceorder.ServiceOrderProduct;
import com.odin.odinbff.repository.HistoryRepository;
import com.odin.odinbff.repository.ServiceOrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Service
public class ServiceOrderService {

    private final ServiceOrderRepository serviceOrderRepository;

    private final HistoryRepository historyRepository;


    public ServiceOrderService(@Autowired final ServiceOrderRepository serviceOrderRepository,
                               @Autowired final HistoryRepository historyRepository) {
        this.serviceOrderRepository = serviceOrderRepository;
        this.historyRepository = historyRepository;
    }

    @Transactional
    public void save(final ServiceOrder serviceOrder) throws NoSuchFieldException, IllegalAccessException {
        final Long lastNumber = serviceOrderRepository.getNextNumber();
        serviceOrder.setNumber(lastNumber == null ? 1 : lastNumber + 1);
        serviceOrderRepository.save(serviceOrder);
        historyRepository.save(History.create(serviceOrder));
        for (ServiceOrderProduct soProduct : serviceOrder.getProducts()) {
            historyRepository.save(History.create(soProduct));
        }
        historyRepository.save(History.create(serviceOrder.getPrescription()));
        for (var soVisionProblems : serviceOrder.getPrescription().getProblems())
            historyRepository.save(History.create(soVisionProblems));
    }

    public void close(final ServiceOrder serviceOrder) throws IllegalAccessException {
        if (serviceOrder.getStatus() == ServiceOrder.StatusType.OPENED) {
            serviceOrder.close();
            var his = new History(
                    serviceOrder.getClass(),
                    History.EventType.UPDATED,
                    Map.of("status", ServiceOrder.StatusType.OPENED),
                    serviceOrder.logOfAllData());
            serviceOrderRepository.save(serviceOrder);
            historyRepository.save(his);
        }
    }


}
