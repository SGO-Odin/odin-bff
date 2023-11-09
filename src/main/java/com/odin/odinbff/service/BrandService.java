package com.odin.odinbff.service;

import com.odin.odinbff.model.Brand;
import com.odin.odinbff.model.auditity.History;
import com.odin.odinbff.repository.BrandRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService {

    private final HistoryService historyService;
    private final BrandRepository brandRepository;

    public BrandService(@Autowired final HistoryService historyService,
                        @Autowired final BrandRepository brandRepository) {
        this.historyService = historyService;
        this.brandRepository = brandRepository;
    }

    @Transactional
    public void create(final Brand brand) throws NoSuchFieldException, IllegalAccessException {
        // todo: try/catch throws
        final History historyUpdate = History.create(brand);

        if (historyUpdate.isValid()) {
            brandRepository.save(brand);
            brandRepository.save(brand);
        }
    }

    @Transactional
    public void update(Long id, Brand updatedBrand) throws NoSuchFieldException, IllegalAccessException {
        final Brand brandToUpdate = brandRepository.getReferenceById(id);
        final History history = History.update(brandToUpdate, updatedBrand);
        if(history.isValid()) {
            historyService.save(history);
            brandRepository.save(brandToUpdate);
        }
    }
}
