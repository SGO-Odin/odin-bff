package com.odin.odinbff.service;

import com.odin.odinbff.model.product.Brand;
import com.odin.odinbff.model.audit.History;
import com.odin.odinbff.repository.BrandRepository;
import com.odin.odinbff.repository.HistoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService {

    private final HistoryRepository historyRepository;
    private final BrandRepository brandRepository;

    public BrandService(@Autowired final HistoryRepository historyRepository,
                        @Autowired final BrandRepository brandRepository) {
        this.historyRepository = historyRepository;
        this.brandRepository = brandRepository;
    }

    @Transactional
    public void create(final Brand brand) throws NoSuchFieldException, IllegalAccessException {
        // todo: try/catch throws
        brandRepository.save(brand);
        final History historyUpdate = History.create(brand);
        historyRepository.save(historyUpdate);
    }

    @Transactional
    public void update(Long id, Brand updatedBrand) throws NoSuchFieldException, IllegalAccessException {
        final Brand brandToUpdate = brandRepository.getReferenceById(id);
        final History history = History.update(brandToUpdate, updatedBrand, true);
        historyRepository.save(history);
        brandRepository.save(brandToUpdate);
    }
}
