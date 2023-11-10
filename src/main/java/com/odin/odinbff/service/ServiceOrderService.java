package com.odin.odinbff.service;

import com.odin.odinbff.model.audit.History;
import com.odin.odinbff.model.serviceorder.ServiceOrder;
import com.odin.odinbff.model.serviceorder.ServiceOrderProduct;
import com.odin.odinbff.repository.HistoryRepository;
import com.odin.odinbff.repository.ServiceOrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ServiceOrderService {

    private final ServiceOrderRepository serviceOrderRepository;

    private final HistoryRepository historyRepository;
    public ServiceOrderService(final ServiceOrderRepository serviceOrderRepository,
                               final HistoryRepository historyRepository) {
        this.serviceOrderRepository = serviceOrderRepository;
        this.historyRepository = historyRepository;
    }

    @Transactional
    public void save(ServiceOrder serviceOrder) throws NoSuchFieldException, IllegalAccessException {
        final Long lastNumber = serviceOrderRepository.getNextNumber();
        serviceOrder.setNumber(lastNumber == null ? 1 : lastNumber + 1);
        serviceOrderRepository.save(serviceOrder);
        historyRepository.save(History.create(serviceOrder));
        for(ServiceOrderProduct soProduct: serviceOrder.getProducts()) {
            historyRepository.save(History.create(soProduct));
        }
        historyRepository.save(History.create(serviceOrder.getPrescription()));
        for(var soVisionProblems: serviceOrder.getPrescription().getProblems())
            historyRepository.save(History.create(soVisionProblems));
    }
}
