package com.odin.odinbff.service;

import com.odin.odinbff.model.Brand;
import com.odin.odinbff.model.auditity.History;
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
    public void update(Long id, Brand updatedBrand) throws NoSuchFieldException, IllegalAccessException {

        final Brand brandToUpdate = brandRepository.getReferenceById(id);

        historyRepository.save(History.update(brandToUpdate, updatedBrand));

        brandRepository.save(brandToUpdate);
    }
}
