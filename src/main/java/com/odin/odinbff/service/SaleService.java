package com.odin.odinbff.service;

import com.odin.odinbff.model.audit.History;
import com.odin.odinbff.model.sale.Sale;
import com.odin.odinbff.repository.HistoryRepository;
import com.odin.odinbff.repository.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class SaleService {

    private final SaleRepository saleRepository;

    private final HistoryRepository historyRepository;

    public SaleService(SaleRepository saleRepository, HistoryRepository historyRepository) {
        this.saleRepository = saleRepository;
        this.historyRepository = historyRepository;
    }

    @Transactional
    public void sale(final Sale sale) throws NoSuchFieldException, IllegalAccessException {

        if(sale.getImportedServiceOrder() != null) {
            sale.getImportedServiceOrder().close();
        }

        saleRepository.save(sale);

        historyRepository.save(History.create(sale));
        for(var payments: sale.getPayments()) {
            if(payments.getId() == null) {
                historyRepository.save(History.create(payments));
            }
            // todo: else { ?????????? how do ?????????? }
        }
    }
}
